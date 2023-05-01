package ru.igorsh.stockview.data.mapper

import ru.igorsh.stockview.data.network.model.stock.StockResponse
import ru.igorsh.stockview.domain.mapper.base.Mapper
import ru.igorsh.stockview.domain.model.StockItem

class StockResponseMapper : Mapper<StockResponse, StockItem>() {
    override fun map(from: StockResponse): StockItem = from.run {
        StockItem(
            name = this.name,
            ticker = this.ticker,
            companyDescription = this.companyDescription,
            price = this.price,
            change = this.change,
            changeInPercent = this.changeInPercent,
            imageUrl = this.imageUrl,
            willPriceGoUp = this.willPriceGoUp,
            isFavorite = this.isFavorite,
        )
    }
}