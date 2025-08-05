package com.galal.newnews.presentation.Home.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galal.newnews.domain.entities.Article
import com.galal.newnews.domain.entities.NewsResponse
import com.galal.newnews.domain.useCase.IUseCases.INewsUseCase
import com.galal.newnews.domain.useCase.ImplUseCase.SavedArticlesUseCases
import com.galal.newnews.presentation.Home.ViewModel.States.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    //private val newsUseCase: NewsUseCase,
    private val newsUseCase: INewsUseCase,
    private val savedArticlesUseCases: SavedArticlesUseCases
): ViewModel(){

    private val _newsState = MutableStateFlow<NewsState>(NewsState.Idle)
    val newState: StateFlow<NewsState> = _newsState

    var lastSuccessData :NewsResponse? = null
        private set


    fun getNews(){
        _newsState.value = NewsState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = newsUseCase.invoke()
                lastSuccessData = response
                _newsState.value = NewsState.Success(response)
                Log.d("NewsData", "getNews: ${response.articles}")
            }catch (e: Exception){
                Log.d("NewsData", "getNews: ${e.message}")
                _newsState.value = NewsState.Error(e.message.toString())
            }

        }
    }
    fun saveArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            savedArticlesUseCases.saveArticle(article)
        }
    }

    suspend fun getAllSavedArticles(): List<Article> {
        return savedArticlesUseCases.getAllSavedArticles()
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            savedArticlesUseCases.deleteArticle(article)
        }
    }


}


