package ru.igorsh.stockview.di

import org.koin.dsl.module
import ru.igorsh.stockview.domain.interactor.LocalDatabaseInteractor
import ru.igorsh.stockview.domain.interactor.LocalStorageInteractor
import ru.igorsh.stockview.domain.interactor.NetworkInteractor
import ru.igorsh.stockview.domain.mapper.NewsDatabaseGetMapper
import ru.igorsh.stockview.domain.mapper.NewsDatabaseInsertMapper
import ru.igorsh.stockview.domain.mapper.NewsMapper
import ru.igorsh.stockview.domain.mapper.StockItemMapper


val domainModule = module {

    factory {
        NewsDatabaseGetMapper()
    }

    factory {
        NewsDatabaseInsertMapper()
    }

    factory {
        NewsMapper()
    }

    factory {
        StockItemMapper()
    }

    factory {
        LocalDatabaseInteractor(localDatabaseRepository = get())
    }

    factory {
        LocalStorageInteractor(userStorage = get())
    }

    factory {
        NetworkInteractor(networkRepository = get())
    }
}