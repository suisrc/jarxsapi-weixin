package com.qq.weixin.mp.param.msg;

/**
 * 
 * {
 *     "speed":1
 * }
 * 参数说明
 * 参数.  是否必须 .    说明
 * speed   是.        群发速度的级别群发速度的级别，是一个0到4的整数，数字越大表示群发速度越慢
 * 
 * speed 与 realspeed 的关系如下：
 * speed   realspeed
 * 0       80w/分钟
 * 1       60w/分钟
 * 2       45w/分钟
 * 3       30w/分钟
 * 4       10w/分钟
 * @author Y13
 *
 */
public class MassSpeedSetParam {
    
    /**
     * 群发速度的级别群发速度的级别，是一个0到4的整数，数字越大表示群发速度越慢
     */
    private Integer speed;

    /**
     * 获取群发速度的级别群发速度的级别，是一个0到4的整数，数字越大表示群发速度越慢
     * @return the speed
     */
    public Integer getSpeed() {
        return speed;
    }

    /**
     * 设定群发速度的级别群发速度的级别，是一个0到4的整数，数字越大表示群发速度越慢
     * @param speed the speed to set
     */
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    
}
