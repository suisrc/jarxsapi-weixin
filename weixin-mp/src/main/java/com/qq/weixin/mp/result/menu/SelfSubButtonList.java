package com.qq.weixin.mp.result.menu;

import java.util.List;

/**
 * 子菜单列表
 * @author Y13
 *
 */
public class SelfSubButtonList {

    /**
     * 子菜单列表
     */
    private List<SelfSubButtonInfo> list;

    /**
     * 获取子菜单列表
     * @return the list
     */
    public List<SelfSubButtonInfo> getList() {
        return list;
    }

    /**
     * 设定子菜单列表
     * @param list the list to set
     */
    public void setList(List<SelfSubButtonInfo> list) {
        this.list = list;
    }
    
    
}
