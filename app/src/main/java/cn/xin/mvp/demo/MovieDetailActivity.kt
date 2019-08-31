package cn.xin.mvp.demo

import cn.xin.lib.base.view.BaseAppActivity
import cn.xin.lib.mvp.BasePresenter
import cn.xin.lib.mvp.IBaseView
import cn.xin.lib.mvp.subscribeLifecycle
import cn.xin.lib.net.HttpResultObserver
import cn.xin.mvp.R
import cn.xin.mvp.demo.bean.SubjectBean
import kotlinx.android.synthetic.main.activity_movie_detail.*

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 10:16
 * SmallMVP cn.xin.mvp.demo
 */
class MovieDetailActivity: BaseAppActivity<MovieDetailPresenter>(), IMovieDetailView {

    /**
     * 布局Id
     */
    override fun getLayoutId(): Int {
        return R.layout.activity_movie_detail
    }
    /**
     * 创建Presenter
     */
    override fun createPresenter(): MovieDetailPresenter {
        return MovieDetailPresenter()
    }

    override fun initData() {
        super.initData()
        val id = getExtras()?.getString("id")
        if (id.isNullOrEmpty()) {
            finish()
            return
        }
        presenter.getDetail(id)
    }

    override fun showDetail(data: SubjectBean) {
        GlideApp.with(this).load(data.images.large).into(ivCover)
        tvName.text = data.original_title
        tvDesc.text = data.website
    }

}

class MovieDetailPresenter : BasePresenter<IMovieDetailView>(), IMovieDetailPresenter {

    private val mModel = getModel(MovieModel::class.java)

    override fun getDetail(id: String) {
        mModel.getMovieDetail(id).subscribeLifecycle(this, HttpResultObserver.callBack(view, onOk = {
            view.showDetail(it)
        }))
    }

}
interface IMovieDetailView: IBaseView{
    fun showDetail(data: SubjectBean)
}
interface IMovieDetailPresenter{
    fun getDetail(id: String)
}