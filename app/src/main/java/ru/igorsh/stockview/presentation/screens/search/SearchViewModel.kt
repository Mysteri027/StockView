package ru.igorsh.stockview.presentation.screens.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.igorsh.stockview.domain.interactor.LocalStorageInteractor
import ru.igorsh.stockview.domain.interactor.NetworkInteractor
import ru.igorsh.stockview.domain.mapper.StockResponseMapper
import ru.igorsh.stockview.presentation.model.StockItem

class SearchViewModel(
    private val networkInteractor: NetworkInteractor,
    private val localStorageInteractor: LocalStorageInteractor,
    private val mapper: StockResponseMapper,
) : ViewModel() {

    private val _stockList = MutableLiveData<List<StockItem>>()
    val stockList: LiveData<List<StockItem>> = _stockList

    private val _isError = MutableLiveData(false)
    val isError: LiveData<Boolean> = _isError

    init {
        getStockList()
    }

    fun getStockList() {
        viewModelScope.launch(Dispatchers.IO) {
            val token = "Bearer ${localStorageInteractor.getToken()}"
            val response = networkInteractor.getStockList(token)
            Log.d("token", token)

            if (isResponseValid(response)) {
                val stocksList = response.body()!!.map {
                    mapper.map(it)
                }
                _stockList.postValue(stocksList)
                _isError.postValue(false)
            } else {
                _isError.postValue(true)
            }
        }
    }

    fun getStockByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val token = "Bearer ${localStorageInteractor.getToken()}"
            val response = networkInteractor.getStockByName(name, token)

            if (isResponseValid(response)) {
                val stock = mapper.map(response.body()!!)
                val stocksList = listOf(stock)
                _stockList.postValue(stocksList)
            } else {
                _isError.postValue(true)
            }
        }
    }

    private fun <T> isResponseValid(response: Response<T>): Boolean {
        return response.isSuccessful and (response.code() == 200)
    }

    fun addToFavorite(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val token = "Bearer ${localStorageInteractor.getToken()}"
            networkInteractor.addToFavorite(name, token)
        }
    }

    fun deleteFromFavorite(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val token = "Bearer ${localStorageInteractor.getToken()}"
            networkInteractor.deleteFromFavorite(name, token)
        }
    }
}