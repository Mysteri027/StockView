package ru.igorsh.stockview.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.igorsh.stockview.data.database.model.News

@Database(
    entities = [News::class],
    version = 1,
    exportSchema = false,
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao
}
