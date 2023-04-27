package ru.igorsh.stockview.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import ru.igorsh.stockview.data.network.model.stock.StockResponse

interface StockApi {

    @GET("api/v1/stock")
    suspend fun getAllStocks(@Header("Authorization") token: String): Response<List<StockResponse>>

    @GET("api/v1/stock/name/{name}")
    suspend fun getStockByName(
        @Path("name") name: String,
        @Header("Authorization") token: String
    ): Response<StockResponse>
}