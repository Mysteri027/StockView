package ru.igorsh.stockview.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ru.igorsh.stockview.data.network.model.stock.StockResponse
import ru.igorsh.stockview.data.network.model.stock.history.HistoryResponse

interface StockApi {

    @GET("api/v1/stock")
    suspend fun getAllStocks(@Header("Authorization") token: String): Response<List<StockResponse>>

    @GET("api/v1/stock/name/{name}")
    suspend fun getStockByName(
        @Path("name") name: String,
        @Header("Authorization") token: String
    ): Response<StockResponse>

    @POST("api/v1/stock/like/{name}")
    suspend fun addToFavorite(@Path("name") name: String, @Header("Authorization") token: String)

    @POST("api/v1/stock/dislike/{name}")
    suspend fun deleteFromFavorite(
        @Path("name") name: String,
        @Header("Authorization") token: String
    )

    @GET("https://query2.finance.yahoo.com/v8/finance/chart/{ticker}?formatted=true&crumb=6iPfwrHM.4i&lang=en-IN&region=IN&&interval=1d&events=div|split&corsDomain=in.finance.yahoo.com")
    suspend fun getHistoricalData(
        @Path("ticker") ticker: String,
        @Query("period1") startDate: String,
        @Query("period2") endDate: String,
    ): Response<HistoryResponse>
}