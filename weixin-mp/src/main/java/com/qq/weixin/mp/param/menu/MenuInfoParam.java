package com.qq.weixin.mp.param.menu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 自定义菜单创建接口
 * @author Y13
 *
 */
public class MenuInfoParam {
    
    /**
     * 最多包括3个一级菜单
     * 一级菜单最多4个汉字，多出来的部分将会以“...”代替。
     */
    @JsonProperty("button")
    private List<ButtonInfo> buttons;

    /**
     * 获取最多包括3个一级菜单 一级菜单最多4个汉字，多出来的部分将会以“...”代替。
     * @return the buttons
     */
    public List<ButtonInfo> getButtons() {
        return buttons;
    }

    /**
     * 设定最多包括3个一级菜单 一级菜单最多4个汉字，多出来的部分将会以“...”代替。
     * @param buttons the buttons to set
     */
    public void setButtons(List<ButtonInfo> buttons) {
        this.buttons = buttons;
    }

}
