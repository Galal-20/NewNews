package com.galal.newnews.domain.useCase.ImplUseCase

import com.galal.newnews.domain.useCase.IUseCases.IDeleteArticle
import com.galal.newnews.domain.useCase.IUseCases.IGetAllSavedArticles
import com.galal.newnews.domain.useCase.IUseCases.ISaveArticle

data class SavedArticlesUseCases(
    val saveArticle: ISaveArticle,
    val deleteArticle: IDeleteArticle,
    val getAllSavedArticles: IGetAllSavedArticles
)