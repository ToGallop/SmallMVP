package cn.xin.lib.mvp

import android.os.Bundle
import androidx.collection.ArraySet
import androidx.lifecycle.Lifecycle

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-29 029 上午 12:57
 * SmallMVP cn.xin.lib.mvp
 */
abstract class BaseMvpActivity<P : BasePresenter<out IBaseView>> : BaseActivity(), IBaseView {

    /**
     * 主Presenter
     */
    open lateinit var presenter: P

    /**
     * 多个Presenter时候需要的容器
     */
    private val mPresenters = ArraySet<BasePresenter<*>>(2)

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        initLoading()
        presenter = createPresenter()
        addToPresenters(presenter)
    }

    override fun onDestroy() {
        mPresenters.forEach {
            it.detachView()
        }
        mPresenters.clear()
        super.onDestroy()
    }

    override fun getLifecycle(): Lifecycle {
        return super.getLifecycle()
    }

    override fun showMsg(msg: CharSequence?) {
        super.showMessage(msg)
    }

    override fun showMsg(msg: Int) {
        super.showMessage(msg)
    }

    override fun showLoading() {
        //自行实现loading
    }

    override fun showLoading(msg: CharSequence?) {
        //自行实现loading

    }

    override fun hideLoading() {
        //自行实现loading

    }

    open fun initLoading() {

    }

    /**
     * 创建Presenter
     */
    abstract fun createPresenter(): P

    /**
     * 把其他的Presenter添加到Presenters集合里
     * 这样会自动绑定View和管理内存释放
     */
    protected fun <T : BasePresenter<out IBaseView>> addToPresenters(presenter: T) {
        presenter.attachView(this)
        mPresenters.add(presenter)
    }

}