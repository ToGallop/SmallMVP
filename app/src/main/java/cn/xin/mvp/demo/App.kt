package cn.xin.mvp.demo

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 09:47
 * SmallMVP cn.xin.mvp.demo
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}