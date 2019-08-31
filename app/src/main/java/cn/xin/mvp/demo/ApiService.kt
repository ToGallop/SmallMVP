package cn.xin.mvp.demo

import cn.xin.lib.net.HttpListResult
import cn.xin.mvp.demo.bean.SubjectBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

typealias SingleList<T> = Single<HttpListResult<T>>
typealias ParamMap = MutableMap<String, Any?>

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 08:20
 * SmallMVP cn.xin.lib.net
 */
interface ApiService {


    @GET("movie/top250")
    fun getMovieTop250(@QueryMap params: ParamMap): SingleList<SubjectBean>

    @GET("movie/subject/{id}")
    fun getMovieDetail(@Path("id") id: String): Single<SubjectBean>


}