package ru.igorsh.stockview.domain.mapper

import ru.igorsh.stockview.R
import ru.igorsh.stockview.domain.mapper.base.Mapper
import ru.igorsh.stockview.domain.model.StockItem
import ru.igorsh.stockview.presentation.model.StockUiModel

class StockItemMapper : Mapper<StockItem, StockUiModel>() {

    override fun map(from: StockItem): StockUiModel = from.run {
        return StockUiModel(
            name = name,
            ticker = ticker,
            companyDescription = companyDescription,
            price = price,
            change = change,
            changeInPercent = changeInPercent,
            imageUrl = imageUrl,
            willPriceGoUp = willPriceGoUp,
            color = if (change > 0) R.color.price_green else R.color.price_red,
            isFavorite = isFavorite,
            isFavoriteColor = if (isFavorite) R.color.favorite_color else R.color.yp_white
        )
    }
}