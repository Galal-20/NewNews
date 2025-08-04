package com.galal.newnews.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class NewsResponse(
    val status: String,
    val source: String,
    val sortBy: String,
    val articles: List<Article>
)

@Parcelize
data class Article(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?
): Parcelable