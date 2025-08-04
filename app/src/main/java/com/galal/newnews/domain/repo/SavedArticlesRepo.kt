package com.galal.newnews.domain.repo

import com.galal.newnews.domain.entities.Article

interface SavedArticlesRepo {
    suspend fun saveArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    suspend fun getAllSavedArticles(): List<Article>
}