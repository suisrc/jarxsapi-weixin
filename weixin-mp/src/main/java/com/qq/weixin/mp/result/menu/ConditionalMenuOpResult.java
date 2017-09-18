package com.qq.weixin.mp.result.menu;

import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 微信个性化菜单操作结果
 * @author Y13
 *
 */
public class ConditionalMenuOpResult extends WxErrCode {
    private static final long serialVersionUID = 4893532445765920717L;
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
