package cn.xin.lib.mvp

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-28 028 下午 08:08
 * SmallMVP cn.xin.lib.mvp
 * 定义Activity 和 Fragment 共同需要的方法和属性
 */
interface IViewCommon {

    /**
     * 初始化
     */
    fun init(savedInstanceState: Bundle?)

    /**
     * 布局Id
     */
    @LayoutRes
    fun getLayoutId(): Int

    /**
     * 初始化View
     */
    fun initView()

    /**
     * 初始化事件
     */
    fun initListener()

    /**
     * 初始化数据
     */
    fun initData()

    /**
     * 显示信息
     */
    fun showMessage(@StringRes msg: Int)

    /**
     * 显示信息
     */
    fun showMessage(msg: CharSequence?)

    /**
     * 启动Activity
     * [activityClass] 要启动的activity class
     */
    fun <T: AppCompatActivity> start(
        activityClass: Class<T>,
        extras: Bundle? = null
    )

    /**
     * 启动Activity
     * [activityClass] 要启动的activity class
     * [extras]:[android.content.Intent.putExtras] (Bundle)
     * @see android.content.Intent.putExtras(Bundle)
     * @see android.content.Intent.getExtras
     */
    fun <T: AppCompatActivity> startForResult(
        activityClass: Class<T>,
        requestCode: Int = -1,
        extras: Bundle? = null
    )


}