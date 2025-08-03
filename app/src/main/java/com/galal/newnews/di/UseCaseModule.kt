package com.galal.newnews.di

import com.galal.newnews.domain.repo.NewsRepo
import com.galal.newnews.domain.useCase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepo: NewsRepo): NewsUseCase {
        return NewsUseCase(newsRepo)
    }
}