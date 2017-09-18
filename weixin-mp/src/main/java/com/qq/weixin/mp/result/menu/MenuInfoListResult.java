package com.qq.weixin.mp.result.menu;

import java.util.List;

import com.qq.weixin.mp.param.menu.MenuInfoParam;

/**
 * 菜单的返回对象
 * @author Y13
 *
 */
public class MenuInfoListResult {

    /**
     * 无个性化菜单
     */
    private MenuInfoResult menu;
    
    /**
     * 个性化菜单
     */
    private List<ConditionalMenuInfoResult> conditionalmenu;

    /**
     * 获取无个性化菜单
     * @return the menu
     */
    public MenuInfoParam getMenu() {
        return menu;
    }

    /**
     * 设定无个性化菜单
     * @param menu the menu to set
     */
    public void setMenu(MenuInfoResult menu) {
        this.menu = menu;
    }

    /**
     * 获取个性化菜单
     * @return the conditionalmenu
     */
    public List<ConditionalMenuInfoResult> getConditionalmenu() {
        return conditionalmenu;
    }

    /**
     * 设定个性化菜单
     * @param conditionalmenu the conditionalmenu to set
     */
    public void setConditionalmenu(List<ConditionalMenuInfoResult> conditionalmenu) {
        this.conditionalmenu = conditionalmenu;
    }
    
}
