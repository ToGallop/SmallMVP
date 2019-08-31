package cn.xin.lib.mvp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-28 028 下午 01:35
 * SmallMVP cn.xin.lib.mvp
 * 基础 Activity
 */
abstract class BaseActivity : AppCompatActivity(), IViewCommon {

    val TAG = this::class.simpleName

    lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mView = LayoutInflater.from(this).inflate(getLayoutId(), null, false)
        setContentView(mView)
        init(savedInstanceState)
        initView()
        initListener()
        initData()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "CurrentActivity=$TAG")
    }

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initData() {

    }

    override fun showMessage(msg: Int) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(msg: CharSequence?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * 启动Activity
     * [activityClass] 要启动的activity class
     */
    override fun <T : AppCompatActivity> start(activityClass: Class<T>, extras: Bundle?) {
        val intent = Intent(this, activityClass)
        if (extras != null) {
            intent.putExtras(extras)
        }
        startActivity(intent)
    }

    override fun <T : AppCompatActivity> startForResult(
        activityClass: Class<T>,
        requestCode: Int,
        extras: Bundle?
    ) {
        val intent = Intent(this, activityClass)
        if (extras != null) {
            intent.putExtras(extras)
        }
        startActivityForResult(intent, requestCode)
    }

    fun getExtras(): Bundle?{
        return intent.extras
    }

}