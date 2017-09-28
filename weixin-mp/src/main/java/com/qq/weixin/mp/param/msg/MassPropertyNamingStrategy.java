package com.qq.weixin.mp.param.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;

/**
 * MassSendAll名称解析器
 * 
 * @author Y13
 *
 */
public class MassPropertyNamingStrategy extends PropertyNamingStrategy {
    private static final long serialVersionUID = 896648114891039093L;

    /**
     * 属性构建
     */
    public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
        JsonProperty property = field.getAnnotation(JsonProperty.class);
        if (property != null) {
            return property.value();
        }
        return defaultName;
    }
}
