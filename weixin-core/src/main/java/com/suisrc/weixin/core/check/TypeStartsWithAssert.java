package com.suisrc.weixin.core.check;

import com.suisrc.weixin.core.check.TypeAssert;

/**
 * 类型assert工具
 * @author Y13
 *
 */
public class TypeStartsWithAssert implements TypeAssert {
    
    /**
     * 执行assert
     * @param type
     * @return
     */
    public boolean apply(String type, String value) {
        return type != null && type.startsWith(value);
    }

}
