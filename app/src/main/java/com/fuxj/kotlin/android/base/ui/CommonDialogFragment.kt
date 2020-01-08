package com.fuxj.kotlin.android.base.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes

/**
 * @author: 15701
 * @date: 2020/1/8
 */
class CommonDialogFragment : BaseDialogFragment() {

    private val DIALOGLISTENER  = "dialogListener"

    private var dialogListener: BaseDialogListener? = null

    override fun getLayoutId(): Int {
        return layoutId
    }

    override fun initTheme(): Int {
        return theme
    }

    override fun initView(rootView: View?,baseDialogFragment: BaseDialogFragment) {
        dialogListener!!.convertView(rootView,baseDialogFragment)
    }

    fun seteme(@StyleRes theme: Int): CommonDialogFragment {
        this.theme = theme
        return this
    }

    fun setLayoutId(@LayoutRes layoutId: Int): CommonDialogFragment {
        this.layoutId = layoutId
        return this
    }

    fun setConvertListener(baseDialogListener: BaseDialogListener): CommonDialogFragment {
        this.dialogListener = baseDialogListener
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogListener = savedInstanceState!!.getParcelable<Parcelable>(DIALOGLISTENER) as BaseDialogListener?
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(DIALOGLISTENER,dialogListener)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        dialogListener = null
    }

}