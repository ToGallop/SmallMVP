package cn.xin.lib.base.presenter

import android.util.Log
import cn.xin.lib.mvp.BaseListPresenter
import cn.xin.lib.mvp.IBaseListView
import cn.xin.lib.net.HttpListResult
import cn.xin.lib.net.HttpResultObserver
import io.reactivex.disposables.Disposable

internal const val PARAM_COUNT = "count"
internal const val PARAM_START = "start"

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 05:07
 * SmallMVP cn.xin.lib.base.presenter
 */
abstract class BaseAppListPresenter<T, V : IBaseListView<T>> : BaseListPresenter<T, V>() {

    override lateinit var view: V

    protected open var count = 20

    protected open var start = 0

    /**是否正在刷新*/
    var isRefreshing = false

    var hasMore = true

    /**强制刷新*/
    var forceRefresh = false

    var mListDisposable: Disposable? = null

    fun getNextStart(): Int {
        return start + count
    }

    override fun loadNewList() {

        //强制刷新
        if (forceRefresh) {
            isRefreshing = false
            //取消上次刷新
            dispose(mListDisposable)
        }

        if (isRefreshing) {
            return
        }

        isRefreshing = true

        mListDisposable = getListDisposable(getListResultObserver())
        addDisposable(mListDisposable)

    }

    override fun loadMoreList() {
        if (isNullOrDisposed(mListDisposable)) {
            if (!hasMore && !isRefreshing) { //没有更多，也不是刷新
                Log.i("BaseAppListPresenter", "${!hasMore} && ${!isRefreshing}")
                return
            }
            mListDisposable = getMoreListDisposable(getListResultObserver())
            addDisposable(mListDisposable)
        }
    }

    protected abstract fun getListDisposable(observer: HttpListResultObserver): Disposable?

    protected open fun getMoreListDisposable(observer: HttpListResultObserver): Disposable? {
        return null
    }

    open fun getListResultObserver(): HttpListResultObserver {
        return HttpListResultObserver(view)
    }

    open fun checkHasMore(list: List<T>): Boolean {
        return list.size == count
    }

    open fun createParams(
        extraParams: Map<String, Any?>? = null,
        start: Int = this.start,
        count: Int = this.count
    ): MutableMap<String, Any?> {
        return mutableMapOf<String, Any?>(PARAM_START to start, PARAM_COUNT to count).apply {
            if (extraParams != null) {
                putAll(extraParams)
            }
        }
    }

    open inner class HttpListResultObserver(private val mView: IBaseListView<T>?) :
        HttpResultObserver<HttpListResult<T>>() {

        override fun onStart() {
            if (isRefreshing) {
                mView?.refreshLoading()
            }
        }

        override fun onResult(result: HttpListResult<T>) {
            val data = result.subjects
            start = result.start
            if (isRefreshing) {
                mView?.showNewList(data)
                isRefreshing = false
            } else {
                mView?.showMoreList(data)
            }

            mView?.loadComplete()
            hasMore = checkHasMore(data)
            if (!hasMore) {
                mView?.loadMoreEnd()
            }
        }

        override fun onError(e: Throwable) {
            super.onError(e)
            if (isRefreshing) {
                mView?.loadFail()
                isRefreshing = false
            } else {
                mView?.loadMoreFail()
            }
        }

    }

}