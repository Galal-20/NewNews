package com.galal.newnews.domain.useCase

import com.galal.newnews.domain.repo.NewsRepo

class NewsUseCase(private val newsRepo: NewsRepo) {

    suspend operator fun invoke() = newsRepo.getArticles()
}