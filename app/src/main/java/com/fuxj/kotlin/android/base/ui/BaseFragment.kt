package com.fuxj.kotlin.android.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.gyf.immersionbar.ImmersionBar
import com.trello.rxlifecycle3.components.support.RxFragment

/**
 * @author: 15701
 * @date: 2020/1/8
 */
abstract class BaseFragment : RxFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getLayoutId(), container, false)
        initImmersionBar()
        initView(rootView)
        return rootView
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData(savedInstanceState)
    }


    /**
     * 获取视图
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 初始化 View
     */
    abstract fun initView(rootView: View?)



    /**
     * 设置状态栏
     */
    open fun initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).transparentBar()
            .keyboardEnable(true).statusBarDarkFont(true).init()

    }

    /**
     * 初始化数据
     */
    abstract fun initData(savedInstanceState: Bundle?)

}