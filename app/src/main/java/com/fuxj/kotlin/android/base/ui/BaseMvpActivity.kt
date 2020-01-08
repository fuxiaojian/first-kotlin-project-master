package com.fuxj.kotlin.android.base.ui

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.fuxj.kotlin.android.base.presenter.BasePresenter
import com.fuxj.kotlin.android.base.presenter.view.BaseView

/**
 * @author: 15701
 * @date: 2020/1/8
 */
abstract class BaseMvpActivity<P : BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var mPresenter: P


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPresenter()
    }

    /**
     * 初始化Presenter
     */
    abstract fun initPresenter()

    override fun showLoading() {
        ToastUtils.showShort("显示加载框")
    }

    override fun hideLoading() {
        ToastUtils.showShort("隐藏加载框")
    }

    override fun onError() {
        ToastUtils.showShort("显示错误信息")
    }


}