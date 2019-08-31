package cn.xin.lib.base.view

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.xin.lib.base.presenter.BaseAppListPresenter
import cn.xin.lib.base.view.impl.SimpleBaseListViewDelegate
import cn.xin.lib.mvp.BaseListActivity
import cn.xin.lib.mvp.IBaseListView
import com.chad.library.adapter.base.BaseQuickAdapter

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 05:05
 * SmallMVP cn.xin.lib.base.view
 */
abstract class BaseAppListActivity<T, P : BaseAppListPresenter<T, out IBaseListView<T>>> :
    BaseListActivity<T, P>(), SwipeRefreshLayout.OnRefreshListener,
    BaseQuickAdapter.RequestLoadMoreListener {

    override val baseViewDeleted: IBaseListView<T>? = null /*by lazy {
        SimpleBaseListViewDelegate(this)
    }*/

    override fun onRefresh() {
        refresh()
    }

    override fun onLoadMoreRequested() {
        loadMore()
    }

    open fun refresh() {
        presenter.loadNewList()
    }

    open fun loadMore() {
        presenter.loadMoreList()
    }

}