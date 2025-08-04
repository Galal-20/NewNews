package com.galal.newnews.presentation.Home


import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.galal.newnews.R
import com.galal.newnews.databinding.NewsItemBinding
import com.galal.newnews.domain.entities.Article
import com.galal.newnews.utils.ShareFunctions.Companion.getTimeAgo

class NewsAdapter(
    private val articles: MutableList<Article>,
    private val onReadMoreClick: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.scale_in_animation)
            itemView.startAnimation(animation)
            binding.titleNews.text = article.title ?: "No Title"
            binding.dateNews.text = getTimeAgo(article.publishedAt)
            binding.shortDescriptionNews.text = article.description ?: "No Description"

            Glide.with(binding.imageNews.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.imageNews)

            binding.readMoreButton.setOnClickListener {
                onReadMoreClick(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    fun updateNews(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }
}