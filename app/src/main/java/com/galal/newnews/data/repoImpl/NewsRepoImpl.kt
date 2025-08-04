package com.galal.newnews.data.repoImpl

import com.galal.newnews.data.remote.ApiService
import com.galal.newnews.domain.entities.NewsResponse
import com.galal.newnews.domain.repo.NewsRepo

class NewsRepoImpl(private val apiService: ApiService) : NewsRepo {
    override suspend fun getArticles(): NewsResponse {
        return apiService.getArticles()
    }
}