package com.qq.weixin.mp.param.template;

/**
 * 模版内容
 * 
 * value       是.       模板数据
 * color       否.       模板内容字体颜色，不填默认为黑色
 * @author Y13
 *
 */
public class TemplateDataInfo {

    /**
     * 模板数据
     */
    private String value;
    
    /**
     * 模板内容字体颜色，不填默认为黑色
     */
    private String color;

    /**
     * 获取模板数据
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设定模板数据
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取模板内容字体颜色，不填默认为黑色
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * 设定模板内容字体颜色，不填默认为黑色
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }
    
}
