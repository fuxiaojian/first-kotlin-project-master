package com.fuxj.kotlin.android.base.presenter

import com.fuxj.kotlin.android.base.presenter.view.BaseView

/**
 * @author: 15701
 * @date: 2020/1/8
 */
open class BasePresenter<V : BaseView> {

    lateinit var mView : V

}