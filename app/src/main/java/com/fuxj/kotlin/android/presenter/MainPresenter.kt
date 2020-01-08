package com.fuxj.kotlin.android.presenter

import com.fuxj.kotlin.android.base.presenter.BasePresenter
import com.fuxj.kotlin.android.presenter.view.MainView

/**
 * @author: 15701
 * @date: 2020/1/8
 */
class MainPresenter : BasePresenter<MainView>() {
    fun login(userName: String, password: String) {

        mView.loginResult(true)
    }
}