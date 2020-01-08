package com.fuxj.kotlin.android.ui

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.fuxj.kotlin.android.R
import com.fuxj.kotlin.android.base.ui.BaseMvpActivity
import com.fuxj.kotlin.android.presenter.MainPresenter
import com.fuxj.kotlin.android.presenter.view.MainView
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainPresenter>(), MainView {

    override fun loginResult(result: Boolean) {

        ToastUtils.showShort("登录成功")
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter() {
        mPresenter = MainPresenter()
        mPresenter.mView = this
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).transparentBar()
            .keyboardEnable(true).statusBarDarkFont(true).init()

    }

    override fun initData(savedInstanceState: Bundle?) {

        mTextView.text = getString(R.string.app_name)

        mTextView.setOnClickListener {
            mPresenter.login("", "")
        }
    }
}
