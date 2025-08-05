package com.galal.newnews.presentation.Home.ViewModel.States

import com.galal.newnews.domain.entities.NewsResponse

sealed class NewsState{
    object Idle : NewsState()
    object Loading : NewsState()
    data class Success(val newsResponse: NewsResponse) : NewsState()
    data class Error(val message: String) : NewsState()

}