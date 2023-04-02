package ru.igorsh.stockview.presentation.screens.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.igorsh.stockview.domain.interactor.GetNewUseCase
import ru.igorsh.stockview.domain.model.NewsItem

class NewsViewModel(
    private val getNewUseCase: GetNewUseCase
) : ViewModel() {

    private val _newsList = MutableLiveData<List<NewsItem>>()
    val newsList: LiveData<List<NewsItem>> = _newsList

    private val _isNetworkError = MutableLiveData<Boolean>()
    val isNetworkError = _isNetworkError

    fun getNews() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getNewUseCase.invoke()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _isNetworkError.postValue(false)
                    _newsList.postValue(response.body()?.map {
                        NewsItem(
                            image = it.image,
                            title = it.title,
                            description = it.description
                        )
                    } ?: listOf())
                } else {
                    _isNetworkError.postValue(true)
                }
            }
        }
    }
}