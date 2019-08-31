package cn.xin.lib.mvp

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-30 030 上午 12:33
 * SmallMVP cn.xin.lib.mvp
 */
abstract class BaseListPresenter<T, V: IBaseListView<T>>: BasePresenter<V>() {

    abstract fun loadNewList()

    abstract fun loadMoreList()


}
