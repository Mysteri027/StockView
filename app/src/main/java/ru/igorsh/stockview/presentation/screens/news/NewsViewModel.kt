package ru.igorsh.stockview.presentation.screens.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.igorsh.stockview.data.network.model.news.NewsResponseItem
import ru.igorsh.stockview.domain.interactor.LocalDatabaseInteractor
import ru.igorsh.stockview.domain.interactor.LocalStorageInteractor
import ru.igorsh.stockview.domain.interactor.NetworkInteractor
import ru.igorsh.stockview.domain.mapper.NewsDatabaseGetMapper
import ru.igorsh.stockview.domain.mapper.NewsDatabaseInsertMapper
import ru.igorsh.stockview.domain.mapper.NewsResponseMapper
import ru.igorsh.stockview.domain.model.NewsItem

class NewsViewModel(
    private val getLocalDataBaseMapper: NewsDatabaseGetMapper,
    private val insertLocalDataBaseMapper: NewsDatabaseInsertMapper,
    private val responseMapper: NewsResponseMapper,
    private val localDataBaseInteractor: LocalDatabaseInteractor,
    private val localStorageInteractor: LocalStorageInteractor,
    private val networkInteractor: NetworkInteractor

) : ViewModel() {

    private val _newsList = MutableLiveData<List<NewsItem>>()
    val newsList: LiveData<List<NewsItem>> = _newsList

    private val _isEmptyData = MutableLiveData<Boolean>()
    val isEmptyData: LiveData<Boolean> = _isEmptyData

    init {
        getNewsFromAPi()
    }

    private fun getNewsFromAPi() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = localStorageInteractor.getToken()
                val response = networkInteractor.getNews("Bearer $token")
                withContext(Dispatchers.Main) {
                    if (isResponseValid(response)) {
                        val newsList = response.body()!!.map {
                            responseMapper.map(it)
                        }

                        if (newsList.isNotEmpty()) {
                            _newsList.postValue(newsList)
                        }
                        localDataBaseInteractor.deleteAllNewsFromDataBase()
                        _isEmptyData.postValue(newsList.isEmpty())


                        insertNewsInLocalDatabase(newsList)

                    } else {
                        getNewsFromLocalDatabase()
                    }
                }
            } catch (e: Exception) {
                getNewsFromLocalDatabase()
            }
        }
    }

    private fun isResponseValid(response: Response<List<NewsResponseItem>>): Boolean {
        return response.code() == 200 && response.isSuccessful
    }

    private suspend fun getNewsFromLocalDatabase() {
        withContext(Dispatchers.IO) {
            val newsList: List<NewsItem> = localDataBaseInteractor.getNewsListFromDataBase().map {
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

    private suspend fun insertNewsInLocalDatabase(newsList: List<NewsItem>) {
        withContext(Dispatchers.IO) {

            if (newsList.isEmpty()) return@withContext

            localDataBaseInteractor.addNewsListToDataBase(newsList.map {
                insertLocalDataBaseMapper.map(it)
            })
        }
    }
}