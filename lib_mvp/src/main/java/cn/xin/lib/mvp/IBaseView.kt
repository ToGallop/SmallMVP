package cn.xin.lib.mvp

import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-29 029 上午 12:58
 * SmallMVP cn.xin.lib.mvp
 * MVP View 接口 定义方法
 */
interface IBaseView {

    /**
     * @param msg 显示文本信息
     */
    fun showMsg(msg: CharSequence?)

    fun showMsg(@StringRes msg: Int)

    /**
     * 显示loading
     */
    fun showLoading()

    /**
     * 带文字信息显示loading
     */
    fun showLoading(msg: CharSequence?)

    /**
     * 隐藏loading
     */
    fun hideLoading()

    /**
     * 生命周期绑定
     */
    fun getLifecycle(): Lifecycle


}