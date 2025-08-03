package com.galal.newnews.presentation.Home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galal.newnews.domain.entities.NewsResponse
import com.galal.newnews.domain.useCase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
): ViewModel(){

    private val _newsState = MutableStateFlow<NewsSealedClass>(NewsSealedClass.Idle)
    val newState: StateFlow<NewsSealedClass> = _newsState

    init {
        getNews()
    }

    fun getNews(){
        _newsState.value = NewsSealedClass.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = newsUseCase.invoke()
                _newsState.value = NewsSealedClass.Success(response)
                Log.d("NewsData", "getNews: ${response.articles}")
            }catch (e: Exception){
                Log.d("NewsData", "getNews: ${e.message}")
                _newsState.value = NewsSealedClass.Error(e.message.toString())
            }

        }
    }
}


sealed class NewsSealedClass{
    object Idle : NewsSealedClass()
    object Loading : NewsSealedClass()
    data class Success(val newsResponse: NewsResponse) : NewsSealedClass()
    data class Error(val message: String) : NewsSealedClass()

}