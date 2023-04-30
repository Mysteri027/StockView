package ru.igorsh.stockview.data.network.model.stock.history

data class Chart(
    val error: Any,
    val result: List<Result>
)