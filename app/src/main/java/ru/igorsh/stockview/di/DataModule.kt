package ru.igorsh.stockview.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.igorsh.stockview.data.database.NewsDao
import ru.igorsh.stockview.data.database.NewsDatabase
import ru.igorsh.stockview.data.local.SharedPrefUserStorage
import ru.igorsh.stockview.data.local.UserStorage
import ru.igorsh.stockview.data.network.NewsApi
import ru.igorsh.stockview.data.repository.LocalDatabaseRepositoryImpl
import ru.igorsh.stockview.data.repository.NetworkRepositoryImpl
import ru.igorsh.stockview.data.repository.UserRepositoryImpl
import ru.igorsh.stockview.domain.repository.LocalDatabaseRepository
import ru.igorsh.stockview.domain.repository.NetworkRepository
import ru.igorsh.stockview.domain.repository.UserRepository

private const val BASE_URL = "http://10.0.2.2:8000/"

val dataModule = module {

    single {
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

    single<LocalDatabaseRepository> {
        LocalDatabaseRepositoryImpl(newsDao = get())
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

    single<NewsDatabase> {
        provideNewsDatabase(context = get())
    }

    single<NewsDao> {
        provideNewsDao(database = get())
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

fun provideNewsDao(database: NewsDatabase) = database.getNewsDao()
fun provideNewsDatabase(context: Context) = Room.databaseBuilder(
    context = context,
    NewsDatabase::class.java,
    "NEWS_DATABASE_NAME"
).build()
