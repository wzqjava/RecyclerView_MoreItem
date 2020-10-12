package com.wzq.recycler_countdown

import android.os.CountDownTimer

/**
 *
 * 作者：wzq
 *
 *
 *
 * 创建时间：2020/9/30
 *
 *
 *
 * 文件描述：
 *
 *
 */
class TimerItem(
        var userId: String, //其他属性
        var name: String, //倒计时长，单位毫秒
        var expirationTime: Long

) {
    var going  = 0// 0:正常,  1,倒计时中


    var countDownTimer: CountDownTimer? = null
    val inviteTime = 20000L  // 30秒
    var finishListener:(()->Unit)?=null
    var startListener:(()->Unit)?=null
    var time:String?=null

    fun start(){
        //为空创建
        countDownTimer=  object : CountDownTimer(inviteTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val tempSecond = (millisUntilFinished / 1000).toInt().toString()
                time=tempSecond
                startListener?.invoke()
//                start.invoke(tempSecond)
//                    holder.tvInvite.setBackgroundResource(R.drawable.challenge_shape_already_invite)
//                    holder.tvInvite.text = "邀请($tempSecond)"
//                    holder.tvInvite.isClickable = false
//                    holder.tvInvite.isEnabled = false
            }

            override fun onFinish() {
                time=null
                finishListener?.invoke()
//                finish.invoke()
            }
//                    holder.tvInvite.text = "邀请"
//                    holder.tvInvite.setBackgroundResource(R.drawable.challenge_shape_invite)
//                    holder.tvInvite.isClickable = true
//                    holder.tvInvite.isEnabled = true
            /* if (countDownTimer != null) {
                 countDownTimer.cancel()
                 countDownTimer = null
                 countDownMap.remove(userBasicInfo2.userId.hashCode())
             }*/
        }
        countDownTimer?.start()
    }
}