package ru.igorsh.stockview.data.network.model.stock.history

data class Result(
    val indicators: Indicators,
    val meta: Meta,
    val timestamp: List<Int>
)