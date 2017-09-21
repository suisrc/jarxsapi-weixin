package com.suisrc.weixin.core.check;

/**
 * 类型assert工具
 * @author Y13
 *
 */
@FunctionalInterface
public interface TypeAssert {
    
    /**
     * 执行assert
     * @param type 注解中标记的内容， 可能保护注解
     * @param value 对比内容
     * @return
     */
    boolean apply(String type, String value);

}
