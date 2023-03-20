package ru.igorsh.stockview.di

import org.koin.dsl.module
import ru.igorsh.stockview.data.repository.UserRepositoryImpl
import ru.igorsh.stockview.domain.repository.UserRepository

val dataModule = module {

    single<UserRepository> {
        UserRepositoryImpl()
    }
}