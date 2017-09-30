package com.suisrc.weixin.message;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.suisrc.jaxrsapi.core.Global;
import com.suisrc.weixin.core.WxConsts;

/**
 * 错误编码的多国语言
 * 
 * 暂时只支持中文
 * 
 * @author Y13
 *
 */
public class ErrCodeMsg {
    
    /**
     * 目前只支持中文显示
     */
    private static final String LANGUAGE = Global.getValue(System::getProperty, WxConsts.KEY_CORE_LANGUAGE_KEY, "zh", "zh");

    /**
     * 中文库
     */
    private static final HashMap<String, String> errcodeDict;
    static {
        errcodeDict = new HashMap<>();
        Properties properties = new Properties();
        try {
            properties.load(ErrCodeMsg.class.getResourceAsStream("errcode_" + LANGUAGE + ".properties"));
            properties.forEach((key, value) -> errcodeDict.put(key.toString(), value.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回错误编码的内容
     * 
     * @param code
     * @param def
     * @return
     */
    public static String getErrCodeMsg(Long code, String def) {
        if (code == null) {
            code = 0L;
        }
        String msg = errcodeDict.get(code.toString());
        return msg != null ? msg : def;
    }

    /**
     * 返回错误编码的内容
     * 
     * @param code
     * @param def
     * @return
     */
    public static String getErrCodeMsg(Long code) {
        return getErrCodeMsg(code, "未知");
    }
}
