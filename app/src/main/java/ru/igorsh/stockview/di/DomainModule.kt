package ru.igorsh.stockview.di

import org.koin.dsl.module
import ru.igorsh.stockview.domain.interactor.LoginUseCase
import ru.igorsh.stockview.domain.interactor.RegisterUseCase


val domainModule = module {

    factory {
        LoginUseCase(userRepository = get())
    }

    factory {
        RegisterUseCase(userRepository = get())
    }
}