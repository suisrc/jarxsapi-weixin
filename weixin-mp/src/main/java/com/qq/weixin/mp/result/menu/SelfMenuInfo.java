package com.qq.weixin.mp.result.menu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 菜单配置，菜单
 * @author Y13
 *
 */
public class SelfMenuInfo {
    
    /**
     *  菜单按钮
     */
    @JsonProperty("button")
    private List<SelfButtonInfo> buttons;

    /**
     * 获取菜单按钮
     * @return the buttons
     */
    public List<SelfButtonInfo> getButtons() {
        return buttons;
    }

    /**
     * 设定菜单按钮
     * @param buttons the buttons to set
     */
    public void setButtons(List<SelfButtonInfo> buttons) {
        this.buttons = buttons;
    }

}
