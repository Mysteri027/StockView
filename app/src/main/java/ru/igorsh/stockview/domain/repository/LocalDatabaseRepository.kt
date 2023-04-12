package ru.igorsh.stockview.domain.repository

import ru.igorsh.stockview.data.database.model.News

interface LocalDatabaseRepository {
    suspend fun getAllNews(): List<News>
    suspend fun insertNews(newsList: List<News>)

    suspend fun deleteAllNews()
}