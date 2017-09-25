package com.qq.weixin.mp.result.menu;

import java.util.List;

/**
 * 图文消息的信息集合
 * @author Y13
 *
 */
public class SelfSubButtonNewsInfoList {

    /**
     * 子菜单列表
     */
    private List<SelfSubButtonNewsInfo> list;

    /**
     * 获取子菜单列表
     * @return the list
     */
    public List<SelfSubButtonNewsInfo> getList() {
        return list;
    }

    /**
     * 设定子菜单列表
     * @param list the list to set
     */
    public void setList(List<SelfSubButtonNewsInfo> list) {
        this.list = list;
    }
    
}
