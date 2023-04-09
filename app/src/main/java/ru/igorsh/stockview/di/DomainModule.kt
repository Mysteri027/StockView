package ru.igorsh.stockview.di

import org.koin.dsl.module
import ru.igorsh.stockview.domain.interactor.AddNewsListToDataBaseUseCase
import ru.igorsh.stockview.domain.interactor.GetAuthStatusUseCase
import ru.igorsh.stockview.domain.interactor.GetNewUseCase
import ru.igorsh.stockview.domain.interactor.GetNewsListFromDataBaseUseCase
import ru.igorsh.stockview.domain.interactor.LoginUseCase
import ru.igorsh.stockview.domain.interactor.LogoutUseCase
import ru.igorsh.stockview.domain.interactor.RegisterUseCase
import ru.igorsh.stockview.domain.interactor.SetAuthStatusUseCase
import ru.igorsh.stockview.domain.mapper.NewsDatabaseGetMapper
import ru.igorsh.stockview.domain.mapper.NewsDatabaseInsertMapper
import ru.igorsh.stockview.domain.mapper.NewsResponseMapper


val domainModule = module {

    factory {
        LoginUseCase(userRepository = get())
    }

    factory {
        RegisterUseCase(userRepository = get())
    }

    factory {
        GetAuthStatusUseCase(userStorage = get())
    }

    factory {
        SetAuthStatusUseCase(userStorage = get())
    }

    factory {
        LogoutUseCase(userRepository = get())
    }

    factory {
        GetNewUseCase(networkRepository = get())
    }

    factory {
        AddNewsListToDataBaseUseCase(localDatabaseRepository = get())
    }

    factory {
        GetNewsListFromDataBaseUseCase(localDatabaseRepository = get())
    }

    factory {
        NewsDatabaseGetMapper()
    }

    factory {
        NewsDatabaseInsertMapper()
    }

    factory {
        NewsResponseMapper()
    }
}