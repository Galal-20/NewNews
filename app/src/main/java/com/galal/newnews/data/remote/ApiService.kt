package com.galal.newnews.data.remote

import com.galal.newnews.domain.entities.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

      @GET("v1/articles")
      suspend fun getArticles(
          @Query("source") source: String = "the-next-web"
      ): NewsResponse
}