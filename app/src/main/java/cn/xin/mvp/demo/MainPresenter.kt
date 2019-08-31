package cn.xin.mvp.demo

import cn.xin.lib.base.presenter.BaseAppListPresenter
import cn.xin.lib.mvp.IBaseListView
import cn.xin.lib.mvp.subscribeLifecycle
import cn.xin.lib.net.HttpListResult
import cn.xin.lib.net.HttpResultObserver
import cn.xin.mvp.demo.bean.SubjectBean
import io.reactivex.disposables.Disposable

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 08:29
 * SmallMVP cn.xin.mvp.demo
 */
class MainPresenter : BaseAppListPresenter<SubjectBean, IMainView>(), IMainPresenter {

    private val mModel = getModel(MovieModel::class.java)

    override fun getListDisposable(observer: HttpListResultObserver): Disposable? {
        val params = createParams(start = 0)
        return mModel.getMovieTop250(params).subscribeLifecycle(this, observer)
    }

    override fun getMoreListDisposable(observer: HttpListResultObserver): Disposable? {
        val params = createParams(start = getNextStart())
        return mModel.getMovieTop250(params).subscribeLifecycle(this, observer)
    }

    companion object {
        fun crete(): MainPresenter {
            return MainPresenter()
        }
    }
}

interface IMainView : IBaseListView<SubjectBean> {

}

interface IMainPresenter {

}