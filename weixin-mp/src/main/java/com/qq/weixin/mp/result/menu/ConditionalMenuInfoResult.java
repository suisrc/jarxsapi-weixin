package com.qq.weixin.mp.result.menu;

import com.qq.weixin.mp.param.menu.ConditionalMenuInfoParam;

/**
 * 个性化菜单内容
 * 
 * @see com.qq.weixin.mp.param.menu.ConditionalMenuInfoParam
 * @author Y13
 *
 */
public class ConditionalMenuInfoResult extends ConditionalMenuInfoParam {

    /**
     * 个性化主键的ID
     */
    private String menuid;

    /**
     * 获取个性化主键的ID
     * @return the menuid
     */
    public String getMenuid() {
        return menuid;
    }

    /**
     * 设定个性化主键的ID
     * @param menuid the menuid to set
     */
    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }
    
}
