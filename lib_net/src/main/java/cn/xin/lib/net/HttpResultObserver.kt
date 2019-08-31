package cn.xin.lib.net

import android.util.MalformedJsonException
import cn.xin.lib.mvp.IBaseView
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import io.reactivex.observers.DisposableSingleObserver
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * @author Jianxin
 * @date 2019-07-08 008
 * @param view view
 * @param autoShowHttpErrorTips 是否自动弹出后台返回的错误信息，默认自动弹出，如果不自动弹出可以自行处理
 * @param autoToastTips 是否使用默认toast 弹出提示，默认true，不需要默认传false并且[view]实现
 * @link IBaseView.showMsg 方法
 */
abstract class HttpResultObserver<T : HttpResult>(
    open val view: IBaseView? = null
) : DisposableSingleObserver<T>() {

    override fun onStart() {
        super.onStart()
        view?.showLoading()
    }

    override fun onSuccess(result: T) {
        dispose()
        if (result.isSuccess()) {
            view?.hideLoading()
            onResult(result)
        } else {
            onError(HttpResultException(result.code, result.msg))
        }
    }

    override fun onError(e: Throwable) {
        dispose()
        view?.hideLoading()
        when (e) {
            is UnknownHostException, is ConnectException -> {
                toast("网络连接错误")
            }
            is SocketTimeoutException, is TimeoutException -> {
                toast("网络连接超时")
            }
            is JSONException, is JsonIOException, is JsonSyntaxException, is MalformedJsonException -> {
                toast("数据解析异常")
            }
            is HttpResultException -> {
                toast(e.getCodeMsg())
            }
            else -> {
                toast(e.message!!)
            }
        }
        onFailure(e)
    }

    abstract fun onResult(result: T)

    open fun onFailure(e: Throwable) {

    }

    open fun toast(msg: String) {
        view?.showMsg(msg)
    }

    companion object {

        val ERROR: (Throwable) -> Unit = { _ -> }

        fun <T : HttpResult> callBack(
            view: IBaseView? = null,
            onOk: (T) -> Unit,
            onError: (Throwable) -> Unit = ERROR
        ): HttpResultObserver<T> {

            return object : HttpResultObserver<T>(view) {
                override fun onResult(result: T) {
                    onOk(result)
                }

                override fun onFailure(e: Throwable) {
                    if (onError != ERROR) {
                        onError(e)
                    }
                }
            }
        }
    }

}

class HttpResultException(var code: Int = 0, override var message: String?) : Exception() {

    fun getCodeMsg(): String {
        return "$code $message"
    }

}