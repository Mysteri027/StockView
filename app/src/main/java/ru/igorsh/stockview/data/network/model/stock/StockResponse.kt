package ru.igorsh.stockview.data.network.model.stock

data class StockResponse(
    val name: String,
    val companyDescription: String,
    val price: Double,
    val change: Double,
    val changeInPercent: Double,
    val imageUrl: String,
    val willPriceGoUp: Boolean,
)
