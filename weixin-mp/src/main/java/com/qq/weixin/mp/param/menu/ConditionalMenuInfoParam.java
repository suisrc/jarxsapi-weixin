package com.qq.weixin.mp.param.menu;

/**
 * 个性化菜单信息
 * @author Y13
 *
 */
public class ConditionalMenuInfoParam extends MenuInfoParam {
    
    /**
     * 菜单匹配规则
     */
    private MatchRule matchrule;

    /**
     * 获取菜单匹配规则
     * @return the matchrule
     */
    public MatchRule getMatchrule() {
        return matchrule;
    }

    /**
     * 设定菜单匹配规则
     * @param matchrule the matchrule to set
     */
    public void setMatchrule(MatchRule matchrule) {
        this.matchrule = matchrule;
    }
}
