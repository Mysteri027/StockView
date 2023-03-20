package ru.igorsh.stockview

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.igorsh.stockview.di.dataModule
import ru.igorsh.stockview.di.domainModule
import ru.igorsh.stockview.di.presentationModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(presentationModule, dataModule, domainModule)
            )
        }
    }
}