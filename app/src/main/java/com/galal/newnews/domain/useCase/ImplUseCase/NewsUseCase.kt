package com.galal.newnews.domain.useCase.ImplUseCase

import com.galal.newnews.domain.entities.NewsResponse
import com.galal.newnews.domain.repo.NewsRepo
import com.galal.newnews.domain.useCase.IUseCases.IDeleteArticle
import com.galal.newnews.domain.useCase.IUseCases.IGetAllSavedArticles
import com.galal.newnews.domain.useCase.IUseCases.INewsUseCase
import com.galal.newnews.domain.useCase.IUseCases.ISaveArticle
import javax.inject.Inject


class NewsUseCase @Inject constructor(
    private val newsRepo: NewsRepo
) : INewsUseCase {

    override suspend operator fun invoke(): NewsResponse {
        return newsRepo.getArticles()
    }
}




