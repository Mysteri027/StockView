package ru.igorsh.stockview.presentation.model

import androidx.annotation.ColorRes

data class CompanyItem(
    val name: String,
    val price: Double,
    val change: Double,
    val changeInPercent: Double,
    @ColorRes val color: Int,
)
