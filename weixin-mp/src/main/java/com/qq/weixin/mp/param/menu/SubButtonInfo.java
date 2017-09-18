package com.qq.weixin.mp.param.menu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 二级菜单
 * @author Y13
 *
 */
public class SubButtonInfo extends AbstractButtonInfo {

    /**
     * 每个一级菜单最多包含5个二级菜单。
     * 二级菜单最多7个汉字，多出来的部分将会以“...”代替。
     */
    @JsonProperty("sub_button")
    private List<SubButtonInfo> subButtons;

    /**
     * 获取每个一级菜单最多包含5个二级菜单。 二级菜单最多7个汉字，多出来的部分将会以“...”代替。
     * @return the subButtons
     */
    public List<SubButtonInfo> getSubButtons() {
        return subButtons;
    }

    /**
     * 设定每个一级菜单最多包含5个二级菜单。 二级菜单最多7个汉字，多出来的部分将会以“...”代替。
     * @param subButtons the subButtons to set
     */
    public void setSubButtons(List<SubButtonInfo> subButtons) {
        this.subButtons = subButtons;
    }

}
