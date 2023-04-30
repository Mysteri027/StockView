package ru.igorsh.stockview.presentation.model

import androidx.annotation.ColorRes
import java.io.Serializable

data class StockItem(
    val name: String,
    val ticker: String,
    val companyDescription: String,
    val price: Double,
    val change: Double,
    val changeInPercent: Double,
    val imageUrl: String,
    val willPriceGoUp: Boolean,
    @ColorRes val color: Int,
    var isFavorite: Boolean,
    @ColorRes val isFavoriteColor: Int,
): Serializable
