package com.suisrc.jaxrsapi.core.runtime;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.WebTarget;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;
import org.jboss.jandex.FieldInfo;
import org.jboss.jandex.Index;
import org.jboss.jandex.MethodInfo;
import org.jboss.jandex.Type;

import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.jaxrsapi.core.ServiceClient;
import com.suisrc.jaxrsapi.core.annotation.SystemValue;
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
class ClientServiceFactory {
	/**
	 * 代理生成模版
	 */
	private static final String InitMethodModule = "{ proxy = " + ProxyBuilder.class.getCanonicalName() + ".builder({ApiType}.class, ("
			+ WebTarget.class.getCanonicalName() + ")activator.getAdapter(" + WebTarget.class.getCanonicalName() + ".class)).build(); }";
	/**
	 * 系统常数模版
	 */
	private static final String SystemReturnModule = "return proxy.{Method}({Params});";
	private static final String SystemParamModule = "if( ${Param} == null ) { ${Param} = ({ParamType})activator.getAdapter(\"{GetKey}\"); } ";
	private static final String SystemFieldModule = "if( ${Param}.{GetField}() == null ) { ${Param}.{SetField}(({FieldType})activator.getAdapter(\"{GetKey}\")); } ";
	
	static CtClass createImpl(ApiActivator activator, Index index, ClassPool ctPool, String implName, ClassInfo classInfo) throws Exception {
		Named named = activator.getClass().getAnnotation(Named.class);
		if( named == null ) {
			throw new RuntimeException("Not found Named Annotation : " + activator.getClass());
		}
		CtClass ctClass = ctPool.makeClass(implName);
		crateBaseInfo(ctPool, classInfo, named, ctClass);
		for( MethodInfo methodInfo : classInfo.methods() ) {
			createMethod(index, ctPool, ctClass, methodInfo);
		}
		return ctClass;
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
		// SystemValue
		for (AnnotationInstance anno : method.annotations()) {
			if (anno.name().toString().equals(SystemValue.class.getCanonicalName())) {
				short position = anno.target().asMethodParameter().position();
				String content = SystemParamModule
						.replace("{Param}", position + 1 + "")
						.replace("{ParamType}", parameters.get(position).name().toString())
						.replace("{GetKey}", anno.value().asString());
				methodContent.append(content);
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
			List<AnnotationInstance> annos = classInfo.annotations().get(DotName.createSimple(SystemValue.class.getName()));
			if( annos == null || annos.isEmpty() ) { continue; }
			for( AnnotationInstance anno : annos ) {
				FieldInfo fieldInfo  = anno.target().asField();
				String name = fieldInfo.name();
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
				String getMethod = "get" + name;
				String setMethod = "set" + name;
				if( fieldInfo.declaringClass().method(getMethod) == null ) {
					name = "is" + name;
				}
				String content = SystemFieldModule
						.replace("{Param}", i + 1 + "")
						.replace("{FieldType}", fieldInfo.type().toString())
						.replace("{GetField}", getMethod)
						.replace("{SetField}", setMethod)
						.replace("{GetKey}", anno.value().asString());
				methodContent.append(content);
			}
		}
		if( paramsContent.length() > 0 ) {
			paramsContent.setLength(paramsContent.length() - 1);
		}
		methodContent.append(SystemReturnModule.replace("{Method}", method.name()).replace("{Params}", paramsContent));
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

		// 增加注解
		ClassFile ctFile = ctClass.getClassFile(); 
		ConstPool constPool = ctFile.getConstPool();
		AnnotationsAttribute attribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
		Annotation annotation = new Annotation(ApplicationScoped.class.getCanonicalName(), constPool);
		attribute.addAnnotation(annotation);
		ctFile.addAttribute(attribute);
		
		CtClass ctApiClass = Utils.getCtClass(ctPool, classInfo.name().toString());
		ctClass.addInterface(ctApiClass); // 继承与通信API
		ctClass.addInterface(Utils.getCtClass(ctPool, ServiceClient.class)); // 继承与ServiceClient
		
		/*
		 * private UserRest proxy;
		 */
		CtField ctProxyField = new CtField(ctApiClass, "proxy", ctClass); // 执行代理
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
	
}
