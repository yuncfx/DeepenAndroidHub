package com.deepen.android.hub.codelib.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.deepen.android.hub.R
import com.deepen.android.hub.codelib.bean.NewsItem
import java.util.*

/**
 * @author shane
 */
class NewsAdapter constructor(private val mContext: Context?, newsCenterItems: ArrayList<NewsItem>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mOnItemClickListener: OnItemClickListener? = null
    private val mCenterItems: ArrayList<NewsItem> = ArrayList()
    fun setNews(items: ArrayList<NewsItem>) {
        LogUtils.d(TAG, " setNews actions = " + items)
        mCenterItems.clear()
        mCenterItems.addAll(Collections.unmodifiableCollection(items))
    }

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val convertView: View = LayoutInflater.from(mContext).inflate(R.layout.layout_news_item, null)
        return NewsHolder(convertView)
    }

    public override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: NewsItem = mCenterItems.get(position)
        val newsHolder: NewsHolder = holder as NewsHolder
        newsHolder.tvNewsTitle.setText(item.title)
        newsHolder.tvFromContent.setText(item.source)
        Glide.with((mContext)!!)
                .load(item.pic)
                .into(newsHolder.newsItemPic)
        if (Objects.equals(NewsItem.Companion.IS_SPECIAL, item.isSpecial)) {
            newsHolder.ivSpecialIndicator.setVisibility(View.VISIBLE)
        } else {
            newsHolder.ivSpecialIndicator.setVisibility(View.INVISIBLE)
        }
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(object : View.OnClickListener {
                public override fun onClick(v: View) {
                    val pos: Int = newsHolder.getLayoutPosition()
                    mOnItemClickListener!!.onItemClick(newsHolder.itemView, pos)
                }
            })
        }
    }

    public override fun getItemCount(): Int {
        return mCenterItems.size
    }

    private class NewsHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsItemPic: ImageView
        var tvNewsTitle: TextView
        var tvFromTag: TextView
        var tvFromContent: TextView
        var ivSpecialIndicator: ImageView

        init {
            newsItemPic = itemView.findViewById(R.id.news_item_pic)
            tvNewsTitle = itemView.findViewById(R.id.tv_news_title)
            tvFromTag = itemView.findViewById(R.id.tv_from_tag)
            tvFromContent = itemView.findViewById(R.id.tv_from_content)
            ivSpecialIndicator = itemView.findViewById(R.id.iv_special_indicator)
        }
    }

    open interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mOnItemClickListener = listener
    }

    companion object {
        private val TAG: String = NewsAdapter::class.java.getSimpleName()
    }

    init {
        mCenterItems.clear()
        mCenterItems.addAll((newsCenterItems)!!)
    }
}