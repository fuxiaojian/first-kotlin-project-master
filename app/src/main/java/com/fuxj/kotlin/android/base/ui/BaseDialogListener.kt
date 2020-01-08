package com.fuxj.kotlin.android.base.ui

import android.os.Parcel
import android.os.Parcelable
import android.view.View

/**
 * @author: 15701
 * @date: 2020/1/8
 */
 abstract class BaseDialogListener constructor() : Parcelable{

    abstract fun convertView(rootView: View?, baseDialogFragment: BaseDialogFragment)

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BaseDialogListener> {
        override fun createFromParcel(parcel: Parcel): BaseDialogListener {

            return object : BaseDialogListener(parcel){
                override fun convertView(rootView: View?, baseDialogFragment: BaseDialogFragment) {

                }

            }
        }

        override fun newArray(size: Int): Array<BaseDialogListener?> {
            return arrayOfNulls(size)
        }
    }

}