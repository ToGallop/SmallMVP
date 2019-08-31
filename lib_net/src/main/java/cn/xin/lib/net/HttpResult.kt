package cn.xin.lib.net

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 07:35
 * SmallMVP cn.xin.lib.net
 * 后台返回结果
 */
open class HttpResult {
    val code: Int = 0
    val msg: String? = ""
    val request: String? = ""

    fun isSuccess(): Boolean {
        return code != 5000
    }

}