package cn.xin.lib.mvp

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-29 029 上午 12:58
 * SmallMVP cn.xin.lib.mvp
 * MVP presenter 接口 通用属性方法定义
 */
interface IBasePresenter<V: IBaseView> {

    var view: V

    /**
     * 关联View
     * @param view 关联View
     */
    fun <View : IBaseView> attachView(view: View)

    /**
     * 取消关联
     */
    fun detachView()

}