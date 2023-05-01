package ru.igorsh.stockview.presentation.screens.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.igorsh.stockview.domain.interactor.LocalDatabaseInteractor
import ru.igorsh.stockview.domain.interactor.LocalStorageInteractor
import ru.igorsh.stockview.domain.interactor.NetworkInteractor
import ru.igorsh.stockview.domain.mapper.NewsDatabaseGetMapper
import ru.igorsh.stockview.domain.mapper.NewsDatabaseInsertMapper
import ru.igorsh.stockview.domain.mapper.NewsMapper
import ru.igorsh.stockview.presentation.model.NewsUiModel

class NewsViewModel(
    private val getLocalDataBaseMapper: NewsDatabaseGetMapper,
    private val insertLocalDataBaseMapper: NewsDatabaseInsertMapper,
    private val responseMapper: NewsMapper,
    private val localDataBaseInteractor: LocalDatabaseInteractor,
    private val localStorageInteractor: LocalStorageInteractor,
    private val networkInteractor: NetworkInteractor

) : ViewModel() {

    private val _newsList = MutableLiveData<List<NewsUiModel>>()
    val newsList: LiveData<List<NewsUiModel>> = _newsList

    private val _isEmptyData = MutableLiveData<Boolean>()
    val isEmptyData: LiveData<Boolean> = _isEmptyData

    init {
        getNewsFromApi()
    }

    private fun getNewsFromApi() {
        val token = getAccessToken()
        viewModelScope.launch(Dispatchers.IO) {
            val data = networkInteractor.getNews(token)
            val newsList = data.map { newsItem ->
                responseMapper.map(newsItem)
            }

            if (newsList.isNotEmpty()) {
                _newsList.postValue(newsList)
                _isEmptyData.postValue(false)

                localDataBaseInteractor.deleteAllNewsFromDataBase()

                insertNewsInLocalDatabase(newsList)
            } else {
                _isEmptyData.postValue(true)

                getNewsFromLocalDatabase()
            }
        }
    }

    private fun getAccessToken(): String {
        return "Bearer ${localStorageInteractor.getToken()}"
    }

    private suspend fun getNewsFromLocalDatabase() {
        withContext(Dispatchers.IO) {
            val newsList: List<NewsUiModel> =
                localDataBaseInteractor.getNewsListFromDataBase().map {
                    getLocalDataBaseMapper.map(it)
                }

            withContext(Dispatchers.Main) {
                if (newsList.isNotEmpty()) {
                    _newsList.postValue(newsList)
                }
                _isEmptyData.postValue(newsList.isEmpty())
            }
        }
    }

    private suspend fun insertNewsInLocalDatabase(newsList: List<NewsUiModel>) {
        withContext(Dispatchers.IO) {

            if (newsList.isEmpty()) return@withContext

            localDataBaseInteractor.addNewsListToDataBase(newsList.map {
                insertLocalDataBaseMapper.map(it)
            })
        }
    }
}