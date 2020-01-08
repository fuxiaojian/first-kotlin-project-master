package com.fuxj.kotlin.android.base.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.fuxj.kotlin.android.app.AppManager
import com.gyf.immersionbar.ImmersionBar
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

/**
 * @author: 15701
 * @date: 2020/1/8
 */
abstract class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)

        //设置视图
        setContentView(getLayoutId())

        //初始化状态栏
        initImmersionBar()

        //初始化数据
        initData(savedInstanceState)
    }

   open fun initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).transparentBar()
            .keyboardEnable(true).statusBarDarkFont(true).init()

    }

    /**
     * 视图
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData(savedInstanceState: Bundle?)


    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }
}