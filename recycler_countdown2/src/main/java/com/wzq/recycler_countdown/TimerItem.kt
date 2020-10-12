package com.wzq.recycler_countdown;

/**
 * <p>作者：wzq<p>
 * <p>创建时间：2020/9/30<p>
 * <p>文件描述：<p>
 */
public class TimerItem {
    //其他属性
    public String name;
    //倒计时长，单位毫秒
    public long expirationTime;
    public int going; // 0:正常,  1,倒计时中
    public TimerItem(String name, long expirationTime) {
        this.name = name;
        this.expirationTime = expirationTime;
    }

}
