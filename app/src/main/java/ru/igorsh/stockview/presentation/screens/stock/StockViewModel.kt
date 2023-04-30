package ru.igorsh.stockview.presentation.screens.stock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.igorsh.stockview.domain.interactor.NetworkInteractor
import ru.igorsh.stockview.domain.model.TimelineData

class StockViewModel(
    private val networkInteractor: NetworkInteractor
) : ViewModel() {

    private val _timelineData = MutableLiveData<TimelineData>()
    val timelineData: LiveData<TimelineData> = _timelineData

    fun getPriceChangePrefix(priceChange: Double): String {
        return if (priceChange >= 0) "+" else ""
    }

    fun getTimelineData(ticker: String, startDate: String, endDate: String) {
        viewModelScope.launch {
            val data = networkInteractor.getTimelineData(ticker, startDate, endDate)
            _timelineData.postValue(data)
        }
    }
}