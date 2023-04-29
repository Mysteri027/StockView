package ru.igorsh.stockview.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.igorsh.stockview.presentation.screens.auth.AuthViewModel
import ru.igorsh.stockview.presentation.screens.news.NewsViewModel
import ru.igorsh.stockview.presentation.screens.profile.ProfileViewModel
import ru.igorsh.stockview.presentation.screens.search.SearchViewModel
import ru.igorsh.stockview.presentation.screens.splash.SplashViewModel
import ru.igorsh.stockview.presentation.screens.stock.StockViewModel

val presentationModule = module {


    viewModel {
        AuthViewModel(
            networkInteractor = get(), localStorageInteractor = get()
        )
    }
    viewModel {
        SplashViewModel(localStorageInteractor = get())
    }

    viewModel {
        ProfileViewModel(
            networkInteractor = get(),
            mapper = get(),
            localStorageInteractor = get()
        )
    }

    viewModel {
        NewsViewModel(
            insertLocalDataBaseMapper = get(),
            getLocalDataBaseMapper = get(),
            responseMapper = get(),
            localDataBaseInteractor = get(),
            networkInteractor = get(),
            localStorageInteractor = get()
        )
    }

    viewModel {
        SearchViewModel(
            networkInteractor = get(),
            mapper = get(),
            localStorageInteractor = get()
        )
    }

    viewModel {
        StockViewModel()
    }
}