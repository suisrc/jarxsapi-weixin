package com.qq.weixin.mp.param.msg;

/**
 * {
 *     "speed":3,
 *     "realspeed":15
 * }
 * 参数说明
 * 参数.       是否必须.    说明
 * speed       是.       群发速度的级别
 * realspeed   是.       群发速度的真实值,单位：万/分钟
 * @author Y13
 *
 */
public class MassSpeedGetParam {
    
    /**
     * 群发速度的级别
     */
    private Integer speed;
    
    /**
     * 群发速度的真实值,单位：万/分钟
     */
    private Integer realspeed;

    /**
     * 获取群发速度的级别
     * @return the speed
     */
    public Integer getSpeed() {
        return speed;
    }

    /**
     * 设定群发速度的级别
     * @param speed the speed to set
     */
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    /**
     * 获取群发速度的真实值,单位：万 分钟
     * @return the realspeed
     */
    public Integer getRealspeed() {
        return realspeed;
    }

    /**
     * 设定群发速度的真实值,单位：万 分钟
     * @param realspeed the realspeed to set
     */
    public void setRealspeed(Integer realspeed) {
        this.realspeed = realspeed;
    }

}
