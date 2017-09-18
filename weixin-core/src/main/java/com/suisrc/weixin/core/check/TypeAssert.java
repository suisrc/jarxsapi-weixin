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
     * @param type
     * @return
     */
    boolean apply(String type, String value);

}
