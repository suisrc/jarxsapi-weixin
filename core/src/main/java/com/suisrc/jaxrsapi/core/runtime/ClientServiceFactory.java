package com.suisrc.jaxrsapi.core.runtime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.client.WebTarget;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;
import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;
import org.jboss.jandex.FieldInfo;
import org.jboss.jandex.Index;
import org.jboss.jandex.MethodInfo;
import org.jboss.jandex.Type;

import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.jaxrsapi.core.Consts;
import com.suisrc.jaxrsapi.core.Global;
import com.suisrc.jaxrsapi.core.ServiceClient;
import com.suisrc.jaxrsapi.core.annotation.LogicProxy;
import com.suisrc.jaxrsapi.core.annotation.NonProxy;
import com.suisrc.jaxrsapi.core.annotation.SystemValue;
import com.suisrc.jaxrsapi.core.annotation.ThreadValue;
import com.suisrc.jaxrsapi.core.annotation.ValueHelper;
import com.suisrc.jaxrsapi.core.proxy.ProxyBuilder;
import com.suisrc.jaxrsapi.core.util.Utils;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

/**
 * 生成执行的代理实体
 * 使用javassist对java代码进行动态生成。
 * @author Y13
 *
 */
public class ClientServiceFactory {
	
	/**
	 * 单接口多服务器模式
	 */
	private static boolean MULIT_MODE = Boolean.valueOf(System.getProperty(Consts.KEY_REMOTE_API_NULTI_MODE, "false"));
	
	/**
	 * 全局偏移量
	 */
	private static volatile int baseOffset = 0;
	
	/**
	 * 代理生成模版
	 */
	private static final String InitMethodModule = "{ proxy = " + ProxyBuilder.class.getCanonicalName() + ".builder({ApiType}.class, ("
			+ WebTarget.class.getCanonicalName() + ")activator.getAdapter(" + WebTarget.class.getCanonicalName() + ".class)).build(); }";
	private static final String GetBaseUrlModule = "String baseUrl = (String) activator.getAdapter(\"" + Consts.BASE_URL + "\"); ";
	/**
	 * 系统常数模版
	 */
	private static final String SystemReturnModule = "return proxy.{Method}({Params});";
	private static final String LogicProxyReturnModule = "return new {LogicProxy}().proxy({URL},{Params});";
	
	private static final String SystemParamModule = "if( ${Param} == null ) { ${Param} = ({ParamType}){Activator}.{MethodName}(\"{Value}\"); } ";
	private static final String SystemFieldModule = "if( ${Param}.{GetField}() == null ) { ${Param}.{SetField}(({FieldType}){Activator}.{MethodName}(\"{Value}\")); } ";
	
	private static final String DefaultParamModule = "if( ${Param} == null ) { ${Param} = ({ParamType}){Activator}.{MethodName}({ParamType}.class, \"{Value}\"); } ";
	private static final String DefaultFieldModule = "if( ${Param}.{GetField}() == null ) { ${Param}.{SetField}(({FieldType}){Activator}.{MethodName}({FieldType}.class, \"{Value}\")); } ";
	
	private static final String ValueHelperParamModule = "${Param} = ({ParamType})new {Value}({Master}).revise(${Param}); ";
	private static final String ValueHelperFieldModule = "${Param}.{SetField}(({FieldType})new {Value}({Master}).revise(${Param})); ";
	
	/**
	 * 创建接口实现
	 * @param activator
	 * @param index
	 * @param ctPool
	 * @param implName
	 * @param classInfo
	 * @return
	 * @throws Exception
	 */
	private static CtClass createImpl(ApiActivator activator, Index index, ClassPool ctPool, String implName, ClassInfo classInfo) throws Exception {
		Named named = activator.getClass().getAnnotation(Named.class);
		if( named == null ) {
			throw new RuntimeException("Not found Named Annotation : " + activator.getClass());
		}
		CtClass ctClass = ctPool.makeClass(implName);
		crateBaseInfo(ctPool, classInfo, named, ctClass);
		for( MethodInfo methodInfo : classInfo.methods() ) {
			if( isProxyMethod( methodInfo ) ) {
				createMethod(index, ctPool, ctClass, methodInfo);
			}
		}
		debugCtClass(ctClass);
		return ctClass;
	}
	
	/**
	 * 是否需要进行代理
	 * 判断是否需要执行代理
	 * @param info
	 * @return
	 */
	private static boolean isProxyMethod(MethodInfo methodInfo) {
		if( methodInfo.name().equals("<init>") || methodInfo.name().startsWith("as")
				|| methodInfo.hasAnnotation(DotName.createSimple(NonProxy.class.getCanonicalName()))) {
			return false; // 一些初始化和构造方法
		}
		return methodInfo.hasAnnotation(DotName.createSimple(GET.class.getCanonicalName()))
				|| methodInfo.hasAnnotation(DotName.createSimple(POST.class.getCanonicalName()))
				|| methodInfo.hasAnnotation(DotName.createSimple(PUT.class.getCanonicalName()))
				|| methodInfo.hasAnnotation(DotName.createSimple(DELETE.class.getCanonicalName()));
	}
	
	/**
	 * 构建代理的方法
	 * @param index 
	 * @param ctClass
	 * @param method
	 * @throws CannotCompileException 
	 * @throws NotFoundException 
	 * @throws ClassNotFoundException 
	 */
	private static void createMethod(Index index, ClassPool ctPool, CtClass ctClass, MethodInfo method) 
			throws CannotCompileException, ClassNotFoundException, NotFoundException {

		List<Type> parameters = method.parameters(); // 获取参数的
		StringBuilder methodContent = new StringBuilder("{ ");
		//-----------------------------------------------------------------------------------//
		List<AnnotationInstance> annos_m = method.annotations();
		for (AnnotationInstance anno : annos_m) {
			if (anno.name().toString().equals(SystemValue.class.getCanonicalName())) {
				methodContent.append(createParamModule(SystemParamModule, anno, null, parameters, "activator", "getAdapter"));
			}
		}
		for (AnnotationInstance anno : annos_m) {
			if (anno.name().toString().equals(ThreadValue.class.getCanonicalName())) {
				AnnotationValue ave = anno.value("clazz");
				String actname = ave != null ? ave.asClass().toString() : Global.class.getCanonicalName();
				ave = anno.value("method");
				String metname = ave != null ? ave.asString() : ThreadValue.defaultMethod;
				methodContent.append(createParamModule(SystemParamModule, anno, null, parameters, actname, metname));
			}
		}
		for (AnnotationInstance anno : annos_m) {
			if (anno.name().toString().equals(DefaultValue.class.getCanonicalName())) {
				methodContent.append(createParamModule(DefaultParamModule, anno, null, parameters, 
						TransformUtils.class.getCanonicalName(), "transform"));
			}
		}
		
		// 参数
		StringBuilder paramsContent = new StringBuilder();
		CtClass[] ctParameters = new CtClass[parameters.size()];
		for( int i = 0; i < parameters.size(); i++ ) {
			paramsContent.append('$').append(i + 1).append(',');
			Type paramType = parameters.get(i);
			ctParameters[i] = Utils.getCtClass(ctPool, paramType.name().toString());
			
			ClassInfo classInfo = index.getClassByName(paramType.name());
			if( classInfo == null ) { continue; }
			List<AnnotationInstance> annos_f = classInfo.annotations().get(DotName.createSimple(SystemValue.class.getName()));
			if( annos_f != null && !annos_f.isEmpty() ) { 
				for (AnnotationInstance anno : annos_f) {
					methodContent.append(createFieldModule(SystemFieldModule, anno, null, i, "activator", "getAdapter"));
				}
			}
			annos_f = classInfo.annotations().get(DotName.createSimple(ThreadValue.class.getName()));
			if( annos_f != null && !annos_f.isEmpty() ) { 
				for (AnnotationInstance anno : annos_f) {
					AnnotationValue ave = anno.value("clazz");
					String actname = ave != null ? ave.asClass().toString() : Global.class.getCanonicalName();
					ave = anno.value("method");
					String metname = ave != null ? ave.asString() : ThreadValue.defaultMethod;
					methodContent.append(createFieldModule(SystemFieldModule, anno, null, i, actname, metname));
				}
			}
			annos_f = classInfo.annotations().get(DotName.createSimple(DefaultValue.class.getName()));
			if( annos_f != null && !annos_f.isEmpty() ) { 
				for (AnnotationInstance anno : annos_f) {
					methodContent.append(createFieldModule(DefaultFieldModule, anno, null, i,
							TransformUtils.class.getCanonicalName(), "transform"));
				}
			}
			//----------------------------------最后的数据修正---------------------------------------------//
			annos_f = classInfo.annotations().get(DotName.createSimple(ValueHelper.class.getName()));
			if( annos_f != null && !annos_f.isEmpty() ) { 
				for (AnnotationInstance anno : annos_f) {
					String value = anno.value().asClass().name().toString();
					String master = anno.value("master").asString();
					String module = ValueHelperFieldModule.replace("{Master}", master);
					methodContent.append(createFieldModule(module, anno, value, i, "", ""));
				}
			}
		}
		//----------------------------------最后的数据修正---------------------------------------------//
		for (AnnotationInstance anno : annos_m) {
			if (anno.name().toString().equals(ValueHelper.class.getCanonicalName())) {
				String value = anno.value().asClass().name().toString();  
				String master = anno.value("master").asString();
				String module = ValueHelperParamModule.replace("{Master}", master);
				methodContent.append(createParamModule(module, anno, value, parameters, "", ""));
			}
		}
		//-----------------------------------------------------------------------------------------------------//
		if( paramsContent.length() > 0 ) {
			paramsContent.setLength(paramsContent.length() - 1);
		}
		
		AnnotationInstance anno = method.annotation(DotName.createSimple(LogicProxy.class.getCanonicalName()));
		if( anno != null ) {
			methodContent.append(creatReturnContent(method, anno, paramsContent.toString()));
		} else {
			methodContent.append(SystemReturnModule.replace("{Method}", method.name()).replace("{Params}", paramsContent));
		}
		methodContent.append("}");
		// 返回值
		CtClass returnType = Utils.getCtClass(ctPool, method.returnType().name().toString());
		// 异常
		List<Type> exceptions = method.exceptions();
		CtClass[] ctExceptions = new CtClass[exceptions.size()];
		for( int i = 0; i < exceptions.size(); i++ ) {
			ctExceptions[i] = Utils.getCtClass(ctPool, exceptions.get(i).name().toString());
		}
		CtMethod ctMethod = CtNewMethod.make(returnType, method.name(), ctParameters, ctExceptions, methodContent.toString(), ctClass);
		ctClass.addMethod(ctMethod);
	}

	/**
	 * 创建返回内容
	 * @param method
	 * @param anno
	 * @return
	 */
	private static String creatReturnContent(MethodInfo method, AnnotationInstance anno, String params) {
		String proxyClass = anno.value().asClass().name().toString();
		String path = "";
		DotName pathAnnoName = DotName.createSimple(Path.class.getCanonicalName());
		for( AnnotationInstance ai : method.declaringClass().classAnnotations() ) {
			if( ai.name().equals(pathAnnoName) ) {
				path += "/" + ai.value().asString();
			}
		}
		AnnotationInstance ai = method.annotation(pathAnnoName);
		if( ai != null ) {
			path += "/" + ai.value().asString();
		}
		if( !path.isEmpty() ) {
			path = path.replaceAll("/{2,}", "/");
			if( path.charAt(path.length() - 1) == '/' ) {
				path = path.substring(0, path.length() - 1);
			}
		}
		String returnContent = LogicProxyReturnModule
				.replace("{LogicProxy}", proxyClass)
				.replace("{URL}", "baseUrl + \"" + path + "\"")
				.replace("{Params}", params);
		return GetBaseUrlModule + returnContent;
	}
	
	/**
	 * 
	 * @param methodContent
	 * @param anno
	 * @param position
	 */
	private static String createFieldModule(String module, AnnotationInstance anno, String annoValue, int position, 
			String activator, String methodName) {
		FieldInfo fieldInfo  = anno.target().asField();
		String name = fieldInfo.name();
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		String getMethod = "get" + name;
		String setMethod = "set" + name;
		if( fieldInfo.declaringClass().method(getMethod) == null ) {
			name = "is" + name;
		}
		return module.replace("{Param}", position + 1 + "")
				.replace("{FieldType}", fieldInfo.type().toString())
				.replace("{Value}", annoValue != null ? annoValue : anno.value().asString())
				.replace("{Activator}", activator)
				.replace("{MethodName}",methodName)
				.replace("{GetField}", getMethod)
				.replace("{SetField}", setMethod);
	}

	/**
	 * 
	 * @param methodContent
	 * @param anno
	 * @param parameters
	 */
	private static String createParamModule(String module, AnnotationInstance anno, String annoValue, List<Type> parameters, 
			String activator, String methodName) {
		short position = anno.target().asMethodParameter().position();
		return module.replace("{Param}", position + 1 + "")
				.replace("{ParamType}", parameters.get(position).name().toString())
				.replace("{Value}", annoValue != null ? annoValue : anno.value().asString())
				.replace("{Activator}", activator)
				.replace("{MethodName}",methodName);
	}

	/**
	 * 构建基础信息
	 * @param ctPool
	 * @param classInfo
	 * @param named
	 * @param ctClass
	 * @throws NotFoundException
	 * @throws ClassNotFoundException
	 * @throws CannotCompileException
	 */
	private static void crateBaseInfo(ClassPool ctPool, ClassInfo classInfo, Named named, CtClass ctClass)
			throws NotFoundException, ClassNotFoundException, CannotCompileException {
		// 代理的接口
		CtClass ctApiClass = Utils.getCtClass(ctPool, classInfo.name().toString());
		// 增加注解
		ClassFile ctFile = ctClass.getClassFile(); 
		ConstPool constPool = ctFile.getConstPool();
		AnnotationsAttribute attribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
		Annotation annotation = new Annotation(ApplicationScoped.class.getCanonicalName(), constPool);
		attribute.addAnnotation(annotation);
		if( MULIT_MODE ) { // 单接口多服务器模式
			annotation = new Annotation(Named.class.getCanonicalName(), constPool);
			annotation.addMemberValue("value", new StringMemberValue(named.value() + Consts.separator + ctApiClass.getSimpleName(), constPool));
		}
		attribute.addAnnotation(annotation);
		ctFile.addAttribute(attribute);
		
		//集成
		ctClass.addInterface(ctApiClass); // 继承与通信API
		ctClass.addInterface(Utils.getCtClass(ctPool, ServiceClient.class)); // 继承与ServiceClient
		
		/*
		 * private UserRest proxy;
		 */
		CtField ctProxyField = new CtField(ctApiClass, "proxy", ctClass); // 执行代理
		ctProxyField.setModifiers(Modifier.PRIVATE );
		ctClass.addField(ctProxyField);
		
		/*
		 * @Inject @Named("xxxx")
		 * private ApiActivator activator; 
		 */
		CtClass ctApiActivatorClass = Utils.getCtClass(ctPool, ApiActivator.class);
		CtField ctApiActivatorField = new CtField(ctApiActivatorClass, "activator", ctClass); // 执行代理
		// 增加注解
		attribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
		annotation = new Annotation(Inject.class.getCanonicalName(), constPool);
		attribute.addAnnotation(annotation);
		annotation = new Annotation(Named.class.getCanonicalName(), constPool);
		annotation.addMemberValue("value", new StringMemberValue(named.value(), constPool));
		attribute.addAnnotation(annotation);
		ctApiActivatorField.getFieldInfo().addAttribute(attribute);
		ctApiActivatorField.setModifiers(Modifier.PRIVATE );
		ctClass.addField(ctApiActivatorField);
		ctClass.addMethod(CtNewMethod.getter("getActivator", ctApiActivatorField));
		ctClass.addMethod(CtNewMethod.setter("setActivator", ctApiActivatorField));

		String methodContent = InitMethodModule.replace("{ApiType}", ctApiClass.getName());
		CtMethod ctInitializedMethod = CtNewMethod.make(CtClass.voidType, "initialized", null, null, methodContent, ctClass);
		// 增加注解
		attribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
		annotation = new Annotation(PostConstruct.class.getCanonicalName(), constPool);
		attribute.addAnnotation(annotation);
		ctInitializedMethod.getMethodInfo().addAttribute(attribute);
		ctClass.addMethod(ctInitializedMethod);
	}
	//------------------------------------------------------------------------------------------------------------//
	
	/**
	 * 处理递归创建
	 * @param index
	 * @param acceptThen
	 * @throws Exception
	 */
	public static void processIndex(Index index, CallBack acceptThen) throws Exception {
		ClassPool ctPool = ClassPool.getDefault();
		List<ClassInfo> activatorClasses = index.getKnownDirectImplementors((DotName.createSimple(ApiActivator.class.getName())));
		Set<Class<?>> activatorSet = new HashSet<>();
		Set<Class<?>> subclasses = new HashSet<>(); // 用于判断是否为其他实体的继承
		for( ClassInfo activatorClass : activatorClasses ) {
			try {// 加载对象
				Class<?> classActivator = (Class<?>)Class.forName(activatorClass.name().toString());
				activatorSet.add(classActivator);
				subclasses.add(classActivator.getSuperclass());
			} catch (Exception e) { e.printStackTrace(); }
		}
		for( Class<?> classActivator : activatorSet ) {
			if( subclasses.contains(classActivator)) { continue; } // 有激活器继承该激活器，舍弃不予加载
			if( classActivator.isInterface() || Modifier.isAbstract(classActivator.getModifiers()) ) { continue; }
			ApiActivator activator = (ApiActivator) classActivator.newInstance();
			createImpl(activator, index, ctPool, acceptThen);
		}
	}

	/**
	 * 创建接口实体
	 * @param activator
	 * @param index
	 * @param ctPool
	 * @param acceptThen
	 * @throws Exception
	 */
	static void createImpl(ApiActivator activator, Index index, ClassPool ctPool, CallBack acceptThen) throws Exception {
		int offset = ++baseOffset; // 偏移量递进
		for( Class<?> apiClass : activator.getClasses() ) {
			ClassInfo info = index.getClassByName(DotName.createSimple(apiClass.getName()));
			// 生成api代理实体
			String name = apiClass.getCanonicalName() + "_$$jaxrsapi_" + offset;
			CtClass ctClass = ClientServiceFactory.createImpl(activator, index, ctPool, name, info);
			try {
				acceptThen.accept(apiClass, ctClass);
			} finally {
				ctClass.freeze(); // 释放
			}
		}
	}
	
	//----------------------------------------------TEST--------------------------------------------------------//
	private static void debugCtClass(CtClass ctClass) {
		try {
			ctClass.writeFile("D:/classes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
