package com.galal.newnews.domain.repo

import com.galal.newnews.domain.entities.NewsResponse

interface NewsRepo {
    suspend fun getArticles(): NewsResponse
}