package ru.igorsh.stockview.domain.model

data class StockItem(
    val name: String,
    val ticker: String,
    val companyDescription: String,
    val price: Double,
    val change: Double,
    val changeInPercent: Double,
    val imageUrl: String,
    val willPriceGoUp: Boolean,
    val isFavorite: Boolean
)