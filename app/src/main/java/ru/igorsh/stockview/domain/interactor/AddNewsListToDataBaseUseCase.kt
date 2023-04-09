package ru.igorsh.stockview.domain.interactor

import ru.igorsh.stockview.data.database.model.News
import ru.igorsh.stockview.domain.repository.LocalDatabaseRepository

class AddNewsListToDataBaseUseCase(
    private val localDatabaseRepository: LocalDatabaseRepository
) {
    suspend fun invoke(newsList: List<News>) = localDatabaseRepository.insertNews(newsList)
}