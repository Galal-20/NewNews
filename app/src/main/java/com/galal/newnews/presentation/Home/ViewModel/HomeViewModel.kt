package com.galal.newnews.presentation.Home.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galal.newnews.domain.entities.Article
import com.galal.newnews.domain.entities.NewsResponse
import com.galal.newnews.domain.useCase.NewsUseCase
import com.galal.newnews.domain.useCase.SavedArticlesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase,
    private val savedArticlesUseCases: SavedArticlesUseCases
): ViewModel(){

    private val _newsState = MutableStateFlow<NewsSealedClass>(NewsSealedClass.Idle)
    val newState: StateFlow<NewsSealedClass> = _newsState

    var lastSuccessData :NewsResponse? = null
        private set


    fun getNews(){
        _newsState.value = NewsSealedClass.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = newsUseCase.invoke()
                lastSuccessData = response
                _newsState.value = NewsSealedClass.Success(response)
                Log.d("NewsData", "getNews: ${response.articles}")
            }catch (e: Exception){
                Log.d("NewsData", "getNews: ${e.message}")
                _newsState.value = NewsSealedClass.Error(e.message.toString())
            }

        }
    }
    fun saveArticle(article: Article) {
        viewModelScope.launch {
            savedArticlesUseCases.saveArticle(article)
        }
    }

    suspend fun getAllSavedArticles(): List<Article> {
        return savedArticlesUseCases.getAllSavedArticles()
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            savedArticlesUseCases.deleteArticle(article)
        }
    }


}


sealed class NewsSealedClass{
    object Idle : NewsSealedClass()
    object Loading : NewsSealedClass()
    data class Success(val newsResponse: NewsResponse) : NewsSealedClass()
    data class Error(val message: String) : NewsSealedClass()

}