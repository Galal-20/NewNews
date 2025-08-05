package com.galal.newnews.di

import android.content.Context
import androidx.room.Room
import com.galal.newnews.data.local.AppDatabase
import com.galal.newnews.data.local.SavedArticleDao
import com.galal.newnews.data.remote.ApiService
import com.galal.newnews.data.repoImpl.SavedArticlesRepoImpl
import com.galal.newnews.domain.repo.SavedArticlesRepo
import com.galal.newnews.utils.ApiKeyManager
import com.galal.newnews.utils.Constants.api_key
import com.galal.newnews.utils.Constants.url_api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalUrl = original.url
                val apiKey = ApiKeyManager.getApiKey(context)

                val url = originalUrl.newBuilder()
                    .addQueryParameter("apiKey", apiKey)
                    .build()
                val requestBuilder = original.newBuilder().url(url)
                chain.proceed(requestBuilder.build())
            }
            .build()
    }



    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url_api)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }



    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "news_db"
        ).build()
    }

    @Provides
    fun provideSavedArticleDao(db: AppDatabase): SavedArticleDao = db.savedArticleDao()

    @Provides
    @Singleton
    fun provideSavedArticlesRepo(dao: SavedArticleDao): SavedArticlesRepo {
        return SavedArticlesRepoImpl(dao)
    }
}