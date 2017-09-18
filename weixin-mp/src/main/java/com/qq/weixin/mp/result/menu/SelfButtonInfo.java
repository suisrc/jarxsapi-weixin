package com.qq.weixin.mp.result.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qq.weixin.mp.param.menu.AbstractButtonInfo;

/**
 * 菜单配置，按钮
 * @author Y13
 *
 */
public class SelfButtonInfo extends AbstractButtonInfo {

    /**
     * 子菜单内容
     */
    @JsonProperty("sub_button")
    private SelfSubButtonList subButtons;
}
