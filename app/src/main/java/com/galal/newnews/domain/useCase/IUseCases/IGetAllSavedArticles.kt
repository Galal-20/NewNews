package com.galal.newnews.domain.useCase.IUseCases

import com.galal.newnews.domain.entities.Article

interface IGetAllSavedArticles {
    suspend operator fun invoke(): List<Article>
}