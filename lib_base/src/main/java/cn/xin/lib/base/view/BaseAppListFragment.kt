package cn.xin.lib.base.view

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.xin.lib.base.R
import cn.xin.lib.base.presenter.BaseAppListPresenter
import cn.xin.lib.base.view.impl.SimpleBaseListViewDelegate
import cn.xin.lib.mvp.BaseActivity
import cn.xin.lib.mvp.BaseListActivity
import cn.xin.lib.mvp.BaseListFragment
import cn.xin.lib.mvp.IBaseListView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 05:05
 * SmallMVP cn.xin.lib.base.view
 */
abstract class BaseAppListFragment<T, P : BaseAppListPresenter<T, out IBaseListView<T>>> :
    BaseListFragment<T, P>(), SwipeRefreshLayout.OnRefreshListener,
    BaseQuickAdapter.RequestLoadMoreListener {

    override val baseViewDeleted: IBaseListView<T>? = null /*by lazy {
        val context = activity
        if (context is BaseActivity) {
            SimpleBaseListViewDelegate(context)
        } else {
            null
        }
    }*/

    override fun onRefresh() {
        presenter.loadNewList()
    }

    override fun onLoadMoreRequested() {
        presenter.loadMoreList()
    }

}