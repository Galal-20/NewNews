package com.galal.newnews.domain.useCase.IUseCases

import com.galal.newnews.domain.entities.NewsResponse

interface INewsUseCase {
    suspend operator fun invoke(): NewsResponse
}