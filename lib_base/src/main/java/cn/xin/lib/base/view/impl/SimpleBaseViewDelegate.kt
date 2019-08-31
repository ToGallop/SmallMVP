package cn.xin.lib.base.view.impl

import android.app.ProgressDialog
import androidx.lifecycle.Lifecycle
import cn.xin.lib.mvp.BaseActivity
import cn.xin.lib.mvp.IBaseView
import com.blankj.utilcode.util.SnackbarUtils

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 03:55
 * SmallMVP cn.xin.lib.base.ui.impl
 * 简单的IBaseView的委托类
 */
open class SimpleBaseViewDelegate(private val context: BaseActivity) : IBaseView {

    private val mLoading: ProgressDialog by lazy {
        ProgressDialog(context)
    }

    /**
     * @param msg 显示文本信息
     */
    override fun showMsg(msg: CharSequence?) {
        //修改显示message的方式
        msg?.let {
            SnackbarUtils.with(context.mView).setMessage(msg).show()
        }
    }

    override fun showMsg(msg: Int) {
        //修改显示message的方式
        showMsg(context.getString(msg))
    }

    /**
     * 显示loading
     */
    override fun showLoading() {
        mLoading.show()
    }

    /**
     * 带文字信息显示loading
     */
    override fun showLoading(msg: CharSequence?) {
        mLoading.setMessage(msg)
        mLoading.show()
    }

    /**
     * 隐藏loading
     */
    override fun hideLoading() {
        mLoading.dismiss()
    }

    /**
     * 生命周期绑定
     */
    override fun getLifecycle(): Lifecycle {
        return context.lifecycle
    }
}