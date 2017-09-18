package com.qq.weixin.mp.param.menu;

/**
 * 微信个性化菜单操作结果
 * @author Y13
 *
 */
public class ConditionalMenuOpParam{
    /**
     * 个性化菜单主键
     */
    private String menuid;
    
    /**
     * 获取个性化菜单主键
     * @return the menuid
     */
    public String getMenuid() {
        return menuid;
    }
    
    /**
     * 设定个性化菜单主键
     * @param menuid the menuid to set
     */
    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }
    
    
}
