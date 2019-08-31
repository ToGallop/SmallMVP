package cn.xin.mvp.demo

import cn.xin.mvp.demo.bean.SubjectBean
import io.reactivex.Single

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 08:31
 * SmallMVP cn.xin.mvp.demo
 */
class MovieModel : BaseModel() {

    fun getMovieTop250(params: ParamMap): SingleList<SubjectBean> {
        return apiService.getMovieTop250(params)
    }

    fun getMovieDetail(id: String): Single<SubjectBean> {
        return apiService.getMovieDetail(id)
    }

}