package ru.igorsh.stockview.di

import org.koin.dsl.module
import ru.igorsh.stockview.domain.interactor.GetAuthStatusUseCase
import ru.igorsh.stockview.domain.interactor.GetNewUseCase
import ru.igorsh.stockview.domain.interactor.LoginUseCase
import ru.igorsh.stockview.domain.interactor.LogoutUseCase
import ru.igorsh.stockview.domain.interactor.RegisterUseCase
import ru.igorsh.stockview.domain.interactor.SetAuthStatusUseCase


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
}