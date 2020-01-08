package com.fuxj.kotlin.android.app

import androidx.multidex.MultiDexApplication
import com.blankj.utilcode.util.Utils


/**
 * @author: 15701
 * @date: 2020/1/8
 */
class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        initAndroidUtilCode()

    }



    /**
     * 初始化 AndroidUtilCode
     */
    private fun initAndroidUtilCode() {
        Utils.init(this)
    }
}