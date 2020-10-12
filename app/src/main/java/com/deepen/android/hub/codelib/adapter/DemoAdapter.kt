package com.deepen.android.hub.codelib.adapter

import android.content.Context
import android.media.browse.MediaBrowser
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deepen.android.hub.R

/**
 * Created by anlia on 2018/3/9.
 */
@Suppress("UNREACHABLE_CODE")
class DemoAdapter constructor(
    private val context: Context,
    private val list: ArrayList<MediaBrowser.MediaItem>?
) : RecyclerView.Adapter<DemoAdapter.ViewHolder>() {
  private var mOnItemClickListener: OnItemClickListener? = null

  inner class ViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    // TODO: 声明组件
    var textTitle: TextView

    init {
      // TODO: 注册组件,view.findViewById(R.id.xxx)
      textTitle = view.findViewById(R.id.text_title)
    }
  }

  open interface OnItemClickListener {
    fun onItemClick(
        view: View?,
        position: Int
    )

    fun onItemLongClick(
        view: View?,
        position: Int
    )
  }

  fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener?) {
    this.mOnItemClickListener = mOnItemClickListener
  }

  public override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
  ): ViewHolder {
    // TODO: 为对应itemViewId赋值,例：R.layout.xxx
    val itemViewId: Int = R.layout.item_music
    val holder: ViewHolder = ViewHolder(
        LayoutInflater.from(context)
            .inflate(itemViewId, parent, false)
    )
    return holder
  }

  public override fun onBindViewHolder(
      holder: ViewHolder,
      position: Int
  ) {
    // TODO: 绑定组件的事件
    holder.textTitle.setText(list?.get(position)?.description?.title)

    // 如果设置了回调，则设置点击事件
    if (mOnItemClickListener != null) {
      holder.itemView.setOnClickListener {
        val pos: Int = holder.layoutPosition
        mOnItemClickListener!!.onItemClick(holder.itemView, pos)
      }
      holder.itemView.setOnLongClickListener {
        val pos: Int = holder.layoutPosition
        mOnItemClickListener!!.onItemLongClick(holder.itemView, pos)
        true
      }
    }
  }

  public override fun getItemCount(): Int {
    return return list?.size ?: 0
  }

}