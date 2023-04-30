package ru.igorsh.stockview.data.network.model.stock.history

data class Meta(
    val chartPreviousClose: Double,
    val currency: String,
    val currentTradingPeriod: CurrentTradingPeriod,
    val dataGranularity: String,
    val exchangeName: String,
    val exchangeTimezoneName: String,
    val firstTradeDate: Int,
    val gmtoffset: Int,
    val instrumentType: String,
    val priceHint: Int,
    val range: String,
    val regularMarketPrice: Double,
    val regularMarketTime: Int,
    val symbol: String,
    val timezone: String,
    val validRanges: List<String>
)