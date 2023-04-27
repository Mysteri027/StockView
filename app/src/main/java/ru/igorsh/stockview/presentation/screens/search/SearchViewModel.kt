package ru.igorsh.stockview.presentation.screens.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    init {
        getStockList()
    }

    private fun getStockList() {
        viewModelScope.launch(Dispatchers.IO) {
            val token = "Bearer ${localStorageInteractor.getToken()}"
            val response = networkInteractor.getStockList(token)
            Log.d("token", token)

            if (response.isSuccessful and (response.code() == 200)) {
                val stocksList = response.body()!!.map {
                    mapper.map(it)
                }
                _stockList.postValue(stocksList)
            }
        }

    }
}