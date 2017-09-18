package com.qq.weixin.mp.result.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 自定义菜单配置信息
 * @author Y13
 *
 */
public class SelfMenuConfigInfoResult {
    
    /**
     * 菜单是否开启，0代表未开启，1代表开启
     */
    @JsonProperty("is_menu_open")
    private Integer isMenuOpen;
    
    /**
     * 菜单信息
     */
    @JsonProperty("selfmenu_info")
    private SelfMenuInfo selfMenuInfo;

    /**
     * 获取菜单是否开启，0代表未开启，1代表开启
     * @return the isMenuOpen
     */
    public Integer getIsMenuOpen() {
        return isMenuOpen;
    }

    /**
     * 设定菜单是否开启，0代表未开启，1代表开启
     * @param isMenuOpen the isMenuOpen to set
     */
    public void setIsMenuOpen(Integer isMenuOpen) {
        this.isMenuOpen = isMenuOpen;
    }

    /**
     * 获取菜单信息
     * @return the selfMenuInfo
     */
    public SelfMenuInfo getSelfMenuInfo() {
        return selfMenuInfo;
    }

    /**
     * 设定菜单信息
     * @param selfMenuInfo the selfMenuInfo to set
     */
    public void setSelfMenuInfo(SelfMenuInfo selfMenuInfo) {
        this.selfMenuInfo = selfMenuInfo;
    }

}
