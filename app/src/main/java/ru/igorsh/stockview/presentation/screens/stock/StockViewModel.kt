package ru.igorsh.stockview.presentation.screens.stock

import androidx.lifecycle.ViewModel

class StockViewModel: ViewModel() {


    fun getPriceChangePrefix(priceChange: Double): String {
        return if (priceChange >= 0) "+" else ""
    }
}