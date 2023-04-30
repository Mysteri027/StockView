package ru.igorsh.stockview.data.network.model.stock.history

data class CurrentTradingPeriod(
    val post: Post,
    val pre: Pre,
    val regular: Regular
)