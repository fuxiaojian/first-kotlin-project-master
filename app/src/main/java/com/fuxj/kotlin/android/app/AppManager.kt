package com.fuxj.kotlin.android.app

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*
import kotlin.system.exitProcess

/**
 * @author: 15701
 * @date: 2020/1/8
 */
class AppManager private constructor() {

    private val mActivityStack: Stack<Activity> = Stack()

    companion object {
        val instance: AppManager by lazy { AppManager() }
    }


    /**
     * 入栈
     */
    fun addActivity(activity: Activity) {
        mActivityStack.add(activity)
    }

    /**
     * 出栈
     */
    fun finishActivity(activity: Activity) {
        activity.finish()
        mActivityStack.remove(activity)
    }

    /**
     * 获取当前栈顶
     */
    fun currentActivity(): Activity {
        return mActivityStack.lastElement()
    }

    /**
     * 清理栈
     */
    private fun finishAllActivity(){
        mActivityStack.forEach { it.finish() }
        mActivityStack.clear()
    }


    /**
     * 退出app
     */
    fun exitApp(context : Context){
        finishAllActivity()
       val activityManager =  context.getSystemService(Context.ACTIVITY_SERVICE) as
               ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        exitProcess(0)
    }



}