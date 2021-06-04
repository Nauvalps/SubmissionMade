package com.bangkit.submissionmade1.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.submissionmade1.core.R
import com.bangkit.submissionmade1.core.databinding.ItemNewsBinding
import com.bangkit.submissionmade1.core.domain.model.News
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {

    private var listData = ArrayList<News>()
    var onItemClick: ((News) -> Unit)? = null

    fun setData(newList: List<News>?) {
        if (newList == null) return
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNewsBinding.bind(itemView)
        @SuppressLint("SimpleDateFormat")
        fun bind(data: News) {
            val dateTimeFormat = data.publishedAt
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val date:Date = format.parse(dateTimeFormat)
            val result = DateFormat.getDateTimeInstance().format(date)
            with(binding) {
                Glide.with(itemView.context).load(data.urlImage).apply(RequestOptions()).error(R.drawable.ic_error).into(binding.imageView)
                title.text = data.title
                dateNews.text = result
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}