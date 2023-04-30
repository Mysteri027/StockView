package ru.igorsh.stockview.presentation.screens.profile

import android.util.Log
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

class ProfileViewModel(
    private val localStorageInteractor: LocalStorageInteractor,
    private val networkInteractor: NetworkInteractor,
    private val mapper: StockItemMapper,
) : ViewModel() {

    private val _stockList = MutableLiveData<List<StockUiModel>>()
    val stockList: LiveData<List<StockUiModel>> = _stockList

    init {
        getFavoriteStocks()
    }

    fun getFavoriteStocks() {
        viewModelScope.launch(Dispatchers.IO) {
            val token = "Bearer ${localStorageInteractor.getToken()}"
            val stocks = networkInteractor.getStockList(token).filter { it.isFavorite }
            Log.d("token", token)
            _stockList.postValue(stocks.map { mapper.map(it) })

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