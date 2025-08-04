package com.galal.newnews.presentation.Favorite.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galal.newnews.domain.entities.Article
import com.galal.newnews.domain.useCase.SavedArticlesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCases: SavedArticlesUseCases
) : ViewModel() {

    private val _savedArticles = MutableLiveData<List<Article>>()
    val savedArticles: LiveData<List<Article>> get() = _savedArticles

    fun loadSavedArticles() {
        viewModelScope.launch {
            _savedArticles.value = useCases.getAllSavedArticles()
        }
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            useCases.saveArticle(article)
        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            useCases.deleteArticle(article)
        }
    }
}