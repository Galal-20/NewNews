package com.galal.newnews.domain.useCase.ImplUseCase

import com.galal.newnews.domain.entities.Article
import com.galal.newnews.domain.repo.SavedArticlesRepo
import com.galal.newnews.domain.useCase.IUseCases.IDeleteArticle
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(
    private val repo: SavedArticlesRepo
) : IDeleteArticle {

    override suspend fun invoke(article: Article) = repo.deleteArticle(article)
}