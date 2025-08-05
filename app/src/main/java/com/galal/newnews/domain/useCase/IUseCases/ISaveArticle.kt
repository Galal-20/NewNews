package com.galal.newnews.domain.useCase.IUseCases

import com.galal.newnews.domain.entities.Article

interface ISaveArticle {
    suspend operator fun invoke(article: Article)
}