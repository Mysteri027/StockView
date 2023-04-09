package ru.igorsh.stockview.data.repository

import ru.igorsh.stockview.data.database.NewsDao
import ru.igorsh.stockview.data.database.model.News
import ru.igorsh.stockview.domain.repository.LocalDatabaseRepository

class LocalDatabaseRepositoryImpl(
    private val newsDao: NewsDao
) : LocalDatabaseRepository {

    override suspend fun getAllNews(): List<News> {
        return newsDao.getNews()
    }

    override suspend fun insertNews(newsList: List<News>) {
        newsDao.insertNews(newsList)
    }
}