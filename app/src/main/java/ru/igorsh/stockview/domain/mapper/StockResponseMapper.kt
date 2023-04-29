package ru.igorsh.stockview.domain.mapper

import ru.igorsh.stockview.R
import ru.igorsh.stockview.data.network.model.stock.StockResponse
import ru.igorsh.stockview.domain.mapper.base.Mapper
import ru.igorsh.stockview.presentation.model.StockItem

class StockResponseMapper : Mapper<StockResponse, StockItem>() {

    override fun map(from: StockResponse): StockItem = from.run {
        return StockItem(
            name = name,
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