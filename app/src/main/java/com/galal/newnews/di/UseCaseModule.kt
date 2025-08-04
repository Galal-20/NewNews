package com.galal.newnews.di

import com.galal.newnews.domain.repo.NewsRepo
import com.galal.newnews.domain.repo.SavedArticlesRepo
import com.galal.newnews.domain.useCase.DeleteArticle
import com.galal.newnews.domain.useCase.GetAllSavedArticles
import com.galal.newnews.domain.useCase.NewsUseCase
import com.galal.newnews.domain.useCase.SaveArticle
import com.galal.newnews.domain.useCase.SavedArticlesUseCases
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

    @Provides
    fun provideSavedArticlesUseCases(repo: SavedArticlesRepo): SavedArticlesUseCases {
        return SavedArticlesUseCases(
            saveArticle = SaveArticle(repo),
            deleteArticle = DeleteArticle(repo),
            getAllSavedArticles = GetAllSavedArticles(repo)
        )
    }

}




