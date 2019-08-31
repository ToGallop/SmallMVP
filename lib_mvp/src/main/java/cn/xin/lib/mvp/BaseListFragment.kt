package cn.xin.lib.mvp

import android.view.View

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-29 029 下午 01:57
 * SmallMVP cn.xin.lib.mvp
 * 列表Fragment
 */
abstract class BaseListFragment<T, P : BasePresenter<out IBaseView>> : BaseMvpFragment<P>(),
    IBaseListView<T> {

    /**
     * 真正的列表操作实现
     */
    protected abstract val baseViewDeleted: IBaseListView<T>?

    /**
     * 显示加载数据
     */
    override fun showNewList(list: List<T>?) {
        baseViewDeleted?.showNewList(list)
    }

    /**
     * 显示加载更多的数据
     */
    override fun showMoreList(list: List<T>?) {
        baseViewDeleted?.showMoreList(list)
    }

    /**
     * 加载一次完成
     */
    override fun loadComplete() {
        baseViewDeleted?.loadComplete()
    }

    /**
     * 加载全部完成，没有更多数据
     */
    override fun loadMoreEnd() {
        baseViewDeleted?.loadMoreEnd()
    }

    /**
     * 加载失败
     */
    override fun loadFail() {
        baseViewDeleted?.loadFail()
    }

    /**
     * 加载更多失败
     */
    override fun loadMoreFail() {
        baseViewDeleted?.loadMoreFail()
    }

    /**
     * 是否有更多数据
     */
    override fun isHasMore(): Boolean {
        return baseViewDeleted?.isHasMore() ?: false
    }

    /**
     * 是否开启加载更多
     */
    override fun enableLoadMore(enable: Boolean) {
        baseViewDeleted?.enableLoadMore(enable)
    }

    /**
     * 设置空布局
     */
    override fun setEmptyView(emptyView: View?) {
        baseViewDeleted?.setEmptyView(emptyView)
    }

    override fun refreshLoading(isRefreshing: Boolean) {
        baseViewDeleted?.refreshLoading(isRefreshing)
    }
}