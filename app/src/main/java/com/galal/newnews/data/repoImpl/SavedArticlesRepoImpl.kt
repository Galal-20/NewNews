package com.galal.newnews.data.repoImpl

import com.galal.newnews.data.local.SavedArticleDao
import com.galal.newnews.data.local.toDomain
import com.galal.newnews.data.local.toEntity
import com.galal.newnews.domain.entities.Article
import com.galal.newnews.domain.repo.SavedArticlesRepo
import javax.inject.Inject

class SavedArticlesRepoImpl @Inject constructor(
    private val dao: SavedArticleDao
) : SavedArticlesRepo {
    override suspend fun saveArticle(article: Article) {
        dao.saveArticle(article.toEntity())
    }

    override suspend fun deleteArticle(article: Article) {
        dao.deleteArticle(article.toEntity())
    }

    override suspend fun getAllSavedArticles(): List<Article> {
        return dao.getAllArticles().map { it.toDomain() }
    }
}