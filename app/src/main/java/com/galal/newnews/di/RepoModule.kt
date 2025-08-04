package com.galal.newnews.di

import com.galal.newnews.data.remote.ApiService
import com.galal.newnews.data.repoImpl.NewsRepoImpl
import com.galal.newnews.domain.repo.NewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideNewsRepo(apiService: ApiService): NewsRepo {
        return NewsRepoImpl(apiService)
    }
}