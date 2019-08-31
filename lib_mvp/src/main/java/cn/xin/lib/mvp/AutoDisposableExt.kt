package cn.xin.lib.mvp

import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.SingleSubscribeProxy
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 09:12
 * SmallMVP cn.xin.lib.mvp
 */
class AutoDisposableExt {

}


/**
 * 订阅线程切换，从子线程切换到主线程
 */
fun <T> Single<T>.io2Main(): Single<T> {
    return compose { upstream ->
        upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}

/**
 * 订阅线程切换，从子线程切换到主线程
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T, E : SingleObserver<in T>> Single<T>.subscribeIo2Main(observer: E): E {
    return io2Main().subscribeWith(observer)
}

/**
 * 带生命周期和线程切换的订阅
 */
fun <T> Single<T>.subscribeLifecycle(
    owner: LifecycleOwner,
    observer: DisposableSingleObserver<T>
): DisposableSingleObserver<T> {
    return this.io2Main()
        .`as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(owner)))
        .subscribeWith(observer)
}

/**
 * 带生命周期和线程切换的订阅
 */
fun <T> Single<T>.subscribeLifecycle(
    owner: LifecycleOwner
): SingleSubscribeProxy<T> {
    return this.io2Main()
        .`as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(owner)))
}