package cn.xin.lib.mvp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-29 029 上午 12:02
 * SmallMVP cn.xin.lib.mvp
 * BaseFragment
 */
abstract class BaseFragment : Fragment(), IViewCommon {

    val TAG = this::class.simpleName

    lateinit var mView: View

    protected open var isViewCreated = false

    protected open var isFirstLoad = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (!this::mView.isInitialized) {
            mView = inflater.inflate(getLayoutId(), container, false)
        }

        val parent = mView.parent
        if (parent is ViewGroup) {
            parent.removeView(mView)
        }

        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!isViewCreated) {
            init(savedInstanceState)
            initView()
            isViewCreated = true
            initListener()
            initData()
            checkLazyLoadData()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            checkLazyLoadData()
        }
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
        Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(msg: CharSequence?) {
        Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun <T : AppCompatActivity> start(
        activityClass: Class<T>,
        extras: Bundle?
    ) {
        val intent = Intent(context, activityClass)
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
        val intent = Intent(context, activityClass)
        if (extras != null) {
            intent.putExtras(extras)
        }
        startActivityForResult(intent, requestCode)
    }

    /**
     * 延迟加载数据
     *
     * 等页面可见时再加载，并且加载一次。如果需要每次页面可见都加载的话，把 [isFirstLoad] 在加载是设置为true}
     *
     */
    open fun lazyLoadData() {

    }

    open fun checkLazyLoadData() {
        Log.d(
            TAG,
            "CurrentFragment=$TAG, isViewCreated=$isViewCreated, isFirstLoad=$isFirstLoad, userVisibleHint=$userVisibleHint"
        )
        if (!isViewCreated || !isFirstLoad || !userVisibleHint) {
            return
        }
        Log.i(TAG, "$TAG lazyLoadData")
        lazyLoadData()
    }

}