package com.fuxj.kotlin.android.presenter.view

import com.fuxj.kotlin.android.base.presenter.view.BaseView

/**
 * @author: 15701
 * @date: 2020/1/8
 */
interface MainView : BaseView {
    fun loginResult(result: Boolean)
}