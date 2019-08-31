package cn.xin.lib.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-29 029 上午 01:03
 * SmallMVP cn.xin.lib.mvp
 * MVP BasePresenter
 */
abstract class BasePresenter<V : IBaseView> : IBasePresenter<V>, LifecycleOwner {

    override lateinit var view: V

    lateinit var lifecycleRegistry: Lifecycle

    /**
     * Disposable容器，收集Disposable，主要用于内存泄漏管理
     * 或者使用 Lifecycle
     */
    private var mDisposables: CompositeDisposable? = null

    @Suppress("UNCHECKED_CAST")
    override fun <View : IBaseView> attachView(view: View) {
        this.view = view as V
        lifecycleRegistry = view.getLifecycle()
        mDisposables = CompositeDisposable()
    }

    override fun detachView() {
        mDisposables?.clear()
        mDisposables = null
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    /**
     * @param disposable 添加Disposable到CompositeDisposable
     * 通过解除disposable处理内存泄漏问题
     */
    fun addDisposable(disposable: Disposable?): Boolean {
        if (disposable == null) {
            return false
        }
        return !isNullOrDisposed(disposable) && mDisposables?.add(disposable) == true
    }

    /**
     * @param disposable 取消本次任务
     */
    fun dispose(disposable: Disposable?) {
        if (disposable != null) {
            mDisposables?.remove(disposable)
        }
    }

    /**
     * @param d 判断d是否为空或者dispose
     * @return true:一次任务未开始或者已结束
     */
    fun isNullOrDisposed(d: Disposable?): Boolean {
        return d == null || d.isDisposed
    }

    /**
     * @param d 判断d是否dispose
     * @return true:一次任务还未结束
     */
    fun isNotDisposed(d: Disposable?): Boolean {
        return d != null && !d.isDisposed
    }

    /**
     * 获取 Model 实例
     */
    protected fun <M : IBaseModel> getModel(clazz: Class<M>): M {
        return ModelManager.create(clazz)
    }

}