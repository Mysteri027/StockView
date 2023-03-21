package ru.igorsh.stockview.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module
import ru.igorsh.stockview.data.local.SharedPrefUserStorage
import ru.igorsh.stockview.data.local.UserStorage
import ru.igorsh.stockview.data.repository.UserRepositoryImpl
import ru.igorsh.stockview.domain.repository.UserRepository

val dataModule = module {

    single<FirebaseAuth> {
        FirebaseAuth.getInstance()
    }
    single<UserStorage> {
        SharedPrefUserStorage(context = get())
    }

    single<UserRepository> {
        UserRepositoryImpl(firebaseAuth = get())
    }
}