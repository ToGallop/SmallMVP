package cn.xin.mvp.demo

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xin.lib.base.view.BaseAppListActivity
import cn.xin.lib.base.view.impl.SimpleBaseListViewDelegate
import cn.xin.lib.mvp.IBaseListView
import cn.xin.mvp.R
import cn.xin.mvp.demo.bean.SubjectBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseAppListActivity<SubjectBean, MainPresenter>(), IMainView {

    private val mAdapter = MovieAdapter()

    override val baseViewDeleted: IBaseListView<SubjectBean> by lazy {
        SimpleBaseListViewDelegate(this, mAdapter, refreshLayout)
    }

    /**
     * 布局Id
     */
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    /**
     * 创建Presenter
     */
    override fun createPresenter(): MainPresenter {
        return MainPresenter.crete()
    }

    override fun initView() {
        super.initView()
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mAdapter.bindToRecyclerView(recyclerView)
        enableLoadMore(true)
    }

    override fun initListener() {
        super.initListener()
        refreshLayout.setOnRefreshListener(this)
        mAdapter.setOnLoadMoreListener(this, recyclerView)
        mAdapter.setOnItemClickListener { _, _, position ->
            mAdapter.getItem(position)?.let {
                val extras = Bundle().apply {
                    putString("id", it.id)
                }
                start(MovieDetailActivity::class.java, extras)
            }
        }
    }

    override fun initData() {
        super.initData()
        refresh()
    }


}
