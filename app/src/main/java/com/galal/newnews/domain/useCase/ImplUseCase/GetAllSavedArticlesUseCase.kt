package com.galal.newnews.domain.useCase.ImplUseCase

import com.galal.newnews.domain.entities.Article
import com.galal.newnews.domain.repo.SavedArticlesRepo
import com.galal.newnews.domain.useCase.IUseCases.IGetAllSavedArticles
import javax.inject.Inject

class GetAllSavedArticlesUseCase @Inject constructor(
    private val repo: SavedArticlesRepo
) : IGetAllSavedArticles {

    override suspend fun invoke(): List<Article> = repo.getAllSavedArticles()
}