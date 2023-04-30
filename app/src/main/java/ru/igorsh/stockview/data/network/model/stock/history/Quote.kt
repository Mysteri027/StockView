package ru.igorsh.stockview.data.network.model.stock.history

data class Quote(
    val close: List<Double>,
    val high: List<Double>,
    val low: List<Double>,
    val `open`: List<Double>,
    val volume: List<Int>
)