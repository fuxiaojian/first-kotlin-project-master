package com.fuxj.kotlin.android.base.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils
import com.fuxj.kotlin.android.R
import com.trello.rxlifecycle3.components.support.RxDialogFragment

/**
 * @author: 15701
 * @date: 2020/1/8
 */
abstract class BaseDialogFragment : RxDialogFragment() {

    private val MARGIN = "margin"
    private val WIDTH = "width"
    private val HEIGHT = "height"
    private val DIM = "dim_amount"
    private val GRAVITY = "gravity"
    private val CANCEL = "out_cancel"
    private val THEME = "theme"
    private val ANIM = "anim_style"
    private val LAYOUT = "layout_id"

    /**
     * 左右边距
     */
    private var margin: Int = 0
    /**
     * 宽度
     */
    private var width: Int = 0
    /**
     * 高度
     */
    private var height: Int = 0
    /**
     * 灰度深浅
     */
    private var dimAmount = 0.5f
    /**
     * 显示的位置
     */
    private var gravity = Gravity.CENTER
    /**
     * 是否点击外部取消
     */
    private var outCancel = true
    /**
     * dialog主题
     */
    @StyleRes
    internal var theme = R.style.BaseDialogStyle

    /**
     * 动画
     */
    @StyleRes
    private var animStyle: Int = 0
    /**
     * 自定义布局
     */
    @LayoutRes
    internal var layoutId: Int = 0

    /**
     * 返回自定义布局
     *
     * @return layout
     */
    @LayoutRes
    abstract fun getLayoutId(): Int


    /**
     * 对话框主题
     *
     * @return 对话框主题
     */
   open fun initTheme(): Int {
        return theme
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, initTheme())

        //恢复保存的数据
        if (savedInstanceState != null) {
            margin = savedInstanceState.getInt(MARGIN)
            width = savedInstanceState.getInt(WIDTH)
            height = savedInstanceState.getInt(HEIGHT)
            dimAmount = savedInstanceState.getFloat(DIM)
            gravity = savedInstanceState.getInt(GRAVITY)
            outCancel = savedInstanceState.getBoolean(CANCEL)
            theme = savedInstanceState.getInt(THEME)
            animStyle = savedInstanceState.getInt(ANIM)
            layoutId = savedInstanceState.getInt(LAYOUT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getLayoutId(), container, false)
        initView(rootView,this)
        return rootView
    }

    abstract fun initView(rootView: View?,baseDialogFragment: BaseDialogFragment)

    override fun onStart() {
        super.onStart()
        initParams()
    }

    /**
     * 屏幕旋转等导致DialogFragment销毁后重建时保存数据
     *
     * @param outState 销毁后重建时保存数据
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MARGIN, margin)
        outState.putInt(WIDTH, width)
        outState.putInt(HEIGHT, height)
        outState.putFloat(DIM, dimAmount)
        outState.putInt(GRAVITY, gravity)
        outState.putBoolean(CANCEL, outCancel)
        outState.putInt(THEME, theme)
        outState.putInt(ANIM, animStyle)
        outState.putInt(LAYOUT, layoutId)
    }

    /**
     * 设置对话框参数
     */
    @SuppressLint("RtlHardcoded")
    private fun initParams() {
        val window = dialog!!.window
        if (window != null) {
            val lp = window.attributes
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount
            if (gravity != 0) {
                lp.gravity = gravity
            }
            when (gravity) {
                Gravity.LEFT, Gravity.LEFT or Gravity.BOTTOM, Gravity.LEFT or Gravity.TOP -> if (animStyle == 0) {
                    animStyle = R.style.LeftAnimation
                }
                Gravity.TOP -> if (animStyle == 0) {
                    animStyle = R.style.TopAnimation
                }
                Gravity.RIGHT, Gravity.RIGHT or Gravity.BOTTOM, Gravity.RIGHT or Gravity.TOP -> if (animStyle == 0) {
                    animStyle = R.style.RightAnimation
                }
                Gravity.BOTTOM -> if (animStyle == 0) {
                    animStyle = R.style.BottomAnimation
                }
                else -> {
                }
            }

            //设置dialog宽度
            if (width == 0) {
                lp.width = ScreenUtils.getScreenWidth() - 2 * SizeUtils.dp2px(margin.toFloat())
            } else if (width == -1) {
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT
            } else {
                lp.width = SizeUtils.dp2px(width.toFloat())
            }

            //设置dialog高度
            if (height == 0) {
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            } else {
                lp.height = SizeUtils.dp2px(height.toFloat())
            }

            //设置dialog进入、退出的动画
            window.setWindowAnimations(animStyle)
            window.attributes = lp
            isCancelable = outCancel
        }

    }

    /**
     * 设置对话框左右间距
     *
     * @param margin 间距
     * @return 对话框
     */
    fun setDialogMargin(margin: Int): BaseDialogFragment {
        this.margin = margin
        return this
    }

    /**
     * 对话框宽度
     *
     * @param width 宽度
     * @return 对话框
     */
    fun setDialogWidth(width: Int): BaseDialogFragment {
        this.width = width
        return this
    }

    /**
     * 对话框高度
     *
     * @param height 高度
     * @return 对话框
     */
    fun setDialogHeight(height: Int): BaseDialogFragment {
        this.height = height
        return this
    }

    /**
     * 对话框灰度
     *
     * @param dimAmount 灰度深浅 0-1
     * @return 对话框
     */
    fun setDialogDimAmount(dimAmount: Float): BaseDialogFragment {
        this.dimAmount = dimAmount
        return this
    }

    /**
     * 设置对话框位置
     *
     * @param gravity 位置
     * @return 对话框
     */
    fun setDialogGravity(gravity: Int): BaseDialogFragment {
        this.gravity = gravity
        return this
    }

    /**
     * 对话框点击外部是否消失
     *
     * @param outCancel true消失 false不消失
     * @return 对话框
     */
    fun setDialogOutCancel(outCancel: Boolean): BaseDialogFragment {
        this.outCancel = outCancel
        return this
    }

    /**
     * 设置对话框动画
     *
     * @param animStyle 对话框动画
     * @return 对话框
     */
    fun setDialogAnimStyle(@StyleRes animStyle: Int): BaseDialogFragment {
        this.animStyle = animStyle
        return this
    }

    /**
     * 显示对话框
     *
     * @param manager 对话框管理类
     * @return 对话框
     */
    fun showDialog(manager: FragmentManager): BaseDialogFragment {
        val ft = manager.beginTransaction()
        if (this.isAdded) {
            ft.remove(this).commit()
        }
        ft.add(this, System.currentTimeMillis().toString())
        ft.commitAllowingStateLoss()
        return this
    }
}