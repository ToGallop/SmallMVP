package cn.xin.lib.net

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 08:57
 * SmallMVP cn.xin.lib.net
 */
class HttpListResult<T> : HttpResult() {

    /**
     * 数据的开始项
     */
    val count: Int = 0
    /**
     * 单页数据数量
     */
    val start: Int = 0
    /**
     * 总数量
     */
    val total: Int = 0

    /**
     * 列表数据体
     */
    var subjects: List<T> = listOf()

}