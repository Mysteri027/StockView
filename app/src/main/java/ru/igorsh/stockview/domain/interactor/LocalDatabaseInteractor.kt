package ru.igorsh.stockview.domain.interactor

import ru.igorsh.stockview.data.database.model.News
import ru.igorsh.stockview.domain.repository.LocalDatabaseRepository

class LocalDatabaseInteractor(
    private val localDatabaseRepository: LocalDatabaseRepository
) {

    suspend fun addNewsListToDataBase(newsList: List<News>) {
        localDatabaseRepository.insertNews(newsList)
    }

    suspend fun deleteAllNewsFromDataBase() = localDatabaseRepository.deleteAllNews()


    suspend fun getNewsListFromDataBase() = localDatabaseRepository.getAllNews()
}