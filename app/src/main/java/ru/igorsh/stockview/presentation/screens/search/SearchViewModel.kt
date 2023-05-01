package ru.igorsh.stockview.presentation.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.igorsh.stockview.domain.interactor.LocalStorageInteractor
import ru.igorsh.stockview.domain.interactor.NetworkInteractor
import ru.igorsh.stockview.domain.mapper.StockItemMapper
import ru.igorsh.stockview.presentation.model.StockUiModel

class SearchViewModel(
    private val networkInteractor: NetworkInteractor,
    private val localStorageInteractor: LocalStorageInteractor,
    private val mapper: StockItemMapper,
) : ViewModel() {

    private val _stockList = MutableLiveData<List<StockUiModel>>()
    val stockList: LiveData<List<StockUiModel>> = _stockList

    private val _isError = MutableLiveData(false)
    val isError: LiveData<Boolean> = _isError

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getStockList()
    }

    fun getStockList() {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val token = "Bearer ${localStorageInteractor.getToken()}"
            val data = networkInteractor.getStockList(token)
            val stockList = data.map {
                mapper.map(it)
            }

            if (stockList.isNotEmpty()) {
                _stockList.postValue(stockList)
                _isError.postValue(false)
            } else {
                _isError.postValue(true)
            }

            _isLoading.postValue(false)
        }
    }

    fun getStockByName(name: String) {
        _isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val token = "Bearer ${localStorageInteractor.getToken()}"
            val stockItem = networkInteractor.getStockByName(name, token)

            if (stockItem != null) {
                val stocks = listOf(stockItem)
                _stockList.postValue(stocks.map { mapper.map(it) })
            } else {
                _isError.postValue(true)
            }

            _isLoading.postValue(false)
        }
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