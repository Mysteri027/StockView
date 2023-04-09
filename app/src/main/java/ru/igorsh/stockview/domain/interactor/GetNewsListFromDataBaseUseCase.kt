package ru.igorsh.stockview.domain.interactor

import ru.igorsh.stockview.domain.repository.LocalDatabaseRepository

class GetNewsListFromDataBaseUseCase(
    private val localDatabaseRepository: LocalDatabaseRepository
) {
    suspend fun invoke() = localDatabaseRepository.getAllNews()
}