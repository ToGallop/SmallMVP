package cn.xin.mvp.demo

import cn.xin.lib.mvp.IBaseModel
import cn.xin.lib.net.HttpManager

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 08:21
 * SmallMVP cn.xin.lib.net
 */
abstract class BaseModel : IBaseModel {

    companion object {
        val apiService =
            HttpManager.getApiService(ApiService::class.java)
    }

}