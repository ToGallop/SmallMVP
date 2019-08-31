package cn.xin.lib.mvp

import android.os.Bundle
import androidx.collection.ArraySet
import androidx.lifecycle.Lifecycle

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-29 029 上午 01:28
 * SmallMVP cn.xin.lib.mvp
 * BaseMvpFragment
 */
abstract class BaseMvpFragment<P : BasePresenter<out IBaseView>> :BaseFragment(), IBaseView {

    /**
     * 多个Presenter时候需要的容器
     */
    private val mPresenters = ArraySet<BasePresenter<*>>(2)

    protected lateinit var presenter: P

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        initLoading()
        if (!this::presenter.isInitialized) {
            presenter = createPresenter()
            addToPresenters(presenter)
        }
    }

    override fun onDestroy() {
        mPresenters.forEach {
            it.detachView()
        }
        mPresenters.clear()
        super.onDestroy()
    }

    override fun showMsg(msg: CharSequence?) {
        showMessage(msg)
    }

    override fun showMsg(msg: Int) {
        showMessage(msg)
    }

    override fun showLoading() {
        
    }

    override fun showLoading(msg: CharSequence?) {
        
    }

    override fun hideLoading() {
        
    }

    override fun getLifecycle(): Lifecycle {
        return super.getLifecycle()
    }

    /**
     * 初始化loading
     */
    abstract fun initLoading()

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