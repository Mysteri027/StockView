package ru.igorsh.stockview.data.network.model.stock.history

data class Pre(
    val end: Int,
    val gmtoffset: Int,
    val start: Int,
    val timezone: String
)