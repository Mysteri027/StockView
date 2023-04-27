package ru.igorsh.stockview.presentation.model

import androidx.annotation.ColorRes

data class StockItem(
    val name: String,
    val companyDescription: String,
    val price: Double,
    val change: Double,
    val changeInPercent: Double,
    val imageUrl: String,
    val willPriceGoUp: Boolean,
    @ColorRes val color: Int,
)