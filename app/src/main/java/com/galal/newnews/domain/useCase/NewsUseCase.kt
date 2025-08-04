package com.galal.newnews.domain.useCase

import com.galal.newnews.domain.entities.Article
import com.galal.newnews.domain.repo.NewsRepo
import com.galal.newnews.domain.repo.SavedArticlesRepo

class NewsUseCase(private val newsRepo: NewsRepo) {

    suspend operator fun invoke() = newsRepo.getArticles()

}



class SaveArticle(private val repo: SavedArticlesRepo) {
    suspend operator fun invoke(article: Article) = repo.saveArticle(article)
}

class DeleteArticle(private val repo: SavedArticlesRepo) {
    suspend operator fun invoke(article: Article) = repo.deleteArticle(article)
}

class GetAllSavedArticles(private val repo: SavedArticlesRepo) {
    suspend operator fun invoke() = repo.getAllSavedArticles()
}

data class SavedArticlesUseCases(
    val saveArticle: SaveArticle,
    val deleteArticle: DeleteArticle,
    val getAllSavedArticles: GetAllSavedArticles
)