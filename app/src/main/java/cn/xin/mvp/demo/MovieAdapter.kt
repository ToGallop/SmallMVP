package cn.xin.mvp.demo

import android.widget.ImageView
import cn.xin.mvp.R
import cn.xin.mvp.demo.bean.SubjectBean
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 08:42
 * SmallMVP cn.xin.mvp.demo
 */
class MovieAdapter : BaseQuickAdapter<SubjectBean, BaseViewHolder>(R.layout.item_movie) {

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    override fun convert(helper: BaseViewHolder, item: SubjectBean) {
        helper.apply {
            val ivCover = getView<ImageView>(R.id.ivCover)
//            GlideApp.with(ivCover)
//                .load("https://user-gold-cdn.xitu.io/2019/4/24/16a4d54fe322f2f2?w=1300&h=1820&f=png&s=1754996")
//                .into(ivCover)
            Glide.with(ivCover)
                .load(item.images.medium)
                .into(ivCover)
            setText(R.id.tvName, item.original_title)
            setText(R.id.tvDesc, item.alt)
        }
    }
}