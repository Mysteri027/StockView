package ru.igorsh.stockview.domain.interactor

import ru.igorsh.stockview.domain.repository.NetworkRepository

class GetNewUseCase(
    private val networkRepository: NetworkRepository
) {
    suspend fun invoke() = networkRepository.getNews()
}