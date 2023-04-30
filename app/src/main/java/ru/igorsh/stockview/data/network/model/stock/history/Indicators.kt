package ru.igorsh.stockview.data.network.model.stock.history

data class Indicators(
    val adjclose: List<Adjclose>,
    val quote: List<Quote>
)