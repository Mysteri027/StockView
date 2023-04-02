package ru.igorsh.stockview.di

import com.google.firebase.auth.FirebaseAuth
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.igorsh.stockview.data.local.SharedPrefUserStorage
import ru.igorsh.stockview.data.local.UserStorage
import ru.igorsh.stockview.data.network.NewsApi
import ru.igorsh.stockview.data.repository.NetworkRepositoryImpl
import ru.igorsh.stockview.data.repository.UserRepositoryImpl
import ru.igorsh.stockview.domain.repository.NetworkRepository
import ru.igorsh.stockview.domain.repository.UserRepository

private const val BASE_URL = "http://10.0.2.2:8000/"

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

    single<NetworkRepository> {
        NetworkRepositoryImpl(newsApi = get())
    }
    factory {
        provideOkHttpClient()
    }

    single {
        provideRetrofit(okHttpClient = get())
    }

    factory {
        provideNewsApi(retrofit = get())
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)