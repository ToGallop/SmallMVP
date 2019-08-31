package cn.xin.lib.base.view

import cn.xin.lib.base.view.impl.SimpleBaseViewDelegate
import cn.xin.lib.mvp.BaseMvpActivity
import cn.xin.lib.mvp.BasePresenter
import cn.xin.lib.mvp.IBaseView

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-30 030 上午 01:02
 * SmallMVP cn.xin.lib.base
 * 直接和项目关联的BaseMvpActivity
 */
abstract class BaseAppActivity<P : BasePresenter<out IBaseView>> : BaseMvpActivity<P>() {

    protected open val baseViewDeleted: IBaseView? by lazy {
        SimpleBaseViewDelegate(this)
    }

    override fun showMessage(msg: Int) {
        showMessage(getString(msg))
    }

    override fun showMessage(msg: CharSequence?) {
        baseViewDeleted?.showMsg(msg)
    }

    override fun showLoading() {
        baseViewDeleted?.showLoading()
    }

    override fun showLoading(msg: CharSequence?) {
        baseViewDeleted?.showLoading(msg)
    }

    override fun hideLoading() {
        baseViewDeleted?.hideLoading()
    }

}