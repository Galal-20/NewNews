package com.galal.newnews.di

import com.galal.newnews.domain.repo.NewsRepo
import com.galal.newnews.domain.repo.SavedArticlesRepo
import com.galal.newnews.domain.useCase.IUseCases.IDeleteArticle
import com.galal.newnews.domain.useCase.IUseCases.IGetAllSavedArticles
import com.galal.newnews.domain.useCase.IUseCases.INewsUseCase
import com.galal.newnews.domain.useCase.IUseCases.ISaveArticle
import com.galal.newnews.domain.useCase.ImplUseCase.DeleteArticleUseCase
import com.galal.newnews.domain.useCase.ImplUseCase.GetAllSavedArticlesUseCase
import com.galal.newnews.domain.useCase.ImplUseCase.SaveArticleUseCase
import com.galal.newnews.domain.useCase.ImplUseCase.NewsUseCase
import com.galal.newnews.domain.useCase.ImplUseCase.SavedArticlesUseCases
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
    fun provideNewsUseCase(newsRepo: NewsRepo): INewsUseCase {
        return NewsUseCase(newsRepo)
    }


    @Provides
    @Singleton
    fun provideSaveArticle(repo: SavedArticlesRepo): ISaveArticle {
        return SaveArticleUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideDeleteArticle(repo: SavedArticlesRepo): IDeleteArticle {
        return DeleteArticleUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetAllSavedArticles(repo: SavedArticlesRepo): IGetAllSavedArticles {
        return GetAllSavedArticlesUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideSavedArticlesUseCases(
        saveArticle: ISaveArticle,
        deleteArticle: IDeleteArticle,
        getAllSavedArticles: IGetAllSavedArticles
    ): SavedArticlesUseCases {
        return SavedArticlesUseCases(
            saveArticle = saveArticle,
            deleteArticle = deleteArticle,
            getAllSavedArticles = getAllSavedArticles
        )
    }


    /*@Provides
    fun provideSavedArticlesUseCases(repo: SavedArticlesRepo): SavedArticlesUseCases {
        return SavedArticlesUseCases(
            saveArticle = SaveArticle(repo),
            deleteArticle = DeleteArticle(repo),
            getAllSavedArticles = GetAllSavedArticles(repo)
        )
    }*/

}




