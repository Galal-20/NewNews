package com.galal.newnews.domain.useCase.ImplUseCase

import com.galal.newnews.domain.entities.Article
import com.galal.newnews.domain.repo.SavedArticlesRepo
import com.galal.newnews.domain.useCase.IUseCases.ISaveArticle
import javax.inject.Inject

class SaveArticleUseCase @Inject constructor(
    private val repo: SavedArticlesRepo
) : ISaveArticle {

    override suspend fun invoke(article: Article) = repo.saveArticle(article)
}