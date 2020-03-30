package com.example.newsapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.examples.coding.newsapp.model.news.Article
import kotlinx.android.synthetic.main.list_row_item.view.*


class NewsListAdapter( var articleList : List<Article>?, var clickListener: OnNewsItemClickListener) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
   // var articleList: List<Article>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_row_item, parent,
                false)
        )


    override fun getItemCount(): Int {
        return articleList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList?.get(position)
        holder.populateItem(article!!)
        holder.initialize(articleList!!.get(position),clickListener)
    }

    fun setNewsList(newsList: List<Article>) {
        articleList = newsList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populateItem(article: Article) {
            itemView.tvAuthor.text = article.author
            itemView.tvSource.text = article.source.name
            itemView.tvTitle.text = article.title


            Glide.with(itemView)
                .load(article.urlToImage)
                .into(itemView.ivNewsArticleImage)
        }

        fun initialize(article: Article, action: OnNewsItemClickListener) {

            itemView.setOnClickListener {
                action.onItemClicked(article, adapterPosition)
            }
        }

    }

}

interface OnNewsItemClickListener {
    fun onItemClicked(article: Article, position: Int)
}