package com.fuxj.kotlin.android.base.presenter.view

/**
 * @author: 15701
 * @date: 2020/1/8
 */
interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun onError()
}