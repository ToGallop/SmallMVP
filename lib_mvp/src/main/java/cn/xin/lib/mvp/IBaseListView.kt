package cn.xin.lib.mvp

import android.view.View
import androidx.lifecycle.Lifecycle

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-29 029 下午 02:13
 * SmallMVP cn.xin.lib.mvp
 * 定义列表需要的相关方法操作
 */
interface IBaseListView<T> : IBaseView {

    /**
     * 显示加载数据
     */
    fun showNewList(list: List<T>?)

    /**
     * 显示加载更多的数据
     */
    fun showMoreList(list: List<T>?)

    /**
     * 加载一次完成
     */
    fun loadComplete()

    /**
     * 加载全部完成，没有更多数据
     */
    fun loadMoreEnd()

    /**
     * 加载失败
     */
    fun loadFail()

    /**
     * 加载更多失败
     */
    fun loadMoreFail()

    /**
     * 是否有更多数据
     */
    fun isHasMore(): Boolean

    /**
     * 是否开启加载更多
     */
    fun enableLoadMore(enable: Boolean = true)

    /**
     * 设置空布局
     */
    fun setEmptyView(emptyView: View?)

    fun refreshLoading(isRefreshing: Boolean = true)

}
