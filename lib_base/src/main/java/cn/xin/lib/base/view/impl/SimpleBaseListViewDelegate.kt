package cn.xin.lib.base.view.impl

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.xin.lib.base.R
import cn.xin.lib.mvp.BaseActivity
import cn.xin.lib.mvp.IBaseListView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 04:13
 * SmallMVP cn.xin.lib.base.ui.impl
 * IBaseListView 实现委托
 */
open class SimpleBaseListViewDelegate<T>(
    context: BaseActivity,
    private var adapter: BaseQuickAdapter<T, BaseViewHolder>? = null,
    private var refreshLayout: SwipeRefreshLayout? = null
) : SimpleBaseViewDelegate(context), IBaseListView<T> {

    /**
     * 显示加载数据
     */
    override fun showNewList(list: List<T>?) {
        adapter?.setNewData(list)
    }

    /**
     * 显示加载更多的数据
     */
    override fun showMoreList(list: List<T>?) {
        list?.let {
            adapter?.addData(list)
        }
    }

    /**
     * 加载一次完成
     */
    override fun loadComplete() {
        refreshLayout?.isRefreshing = false
        adapter?.loadMoreComplete()
    }

    /**
     * 加载全部完成，没有更多数据
     */
    override fun loadMoreEnd() {
        adapter?.loadMoreEnd()
    }

    /**
     * 加载失败
     */
    override fun loadFail() {
        refreshLayout?.isRefreshing = false
        adapter?.loadMoreFail()
    }

    /**
     * 加载更多失败
     */
    override fun loadMoreFail() {
        adapter?.loadMoreFail()
    }

    /**
     * 是否有更多数据
     */
    override fun isHasMore(): Boolean {
        return false
    }

    /**
     * 是否开启加载更多
     */
    override fun enableLoadMore(enable: Boolean) {
        adapter?.setEnableLoadMore(enable)
    }

    /**
     * 设置空布局
     */
    override fun setEmptyView(emptyView: View?) {
        emptyView?.let {
            adapter?.setEmptyView(emptyView)
        }
    }

    override fun refreshLoading(isRefreshing: Boolean) {
        refreshLayout?.isRefreshing = isRefreshing
    }

    class Adapter<T>() :
        BaseQuickAdapter<T, BaseViewHolder>(R.layout.support_simple_spinner_dropdown_item) {
        /**
         * Implement this method and use the helper to adapt the view to the given item.
         *
         * @param helper A fully initialized helper.
         * @param item   The item that needs to be displayed.
         */
        override fun convert(helper: BaseViewHolder, item: T) {
        }


    }
}