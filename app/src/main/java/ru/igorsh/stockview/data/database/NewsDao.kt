package ru.igorsh.stockview.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.igorsh.stockview.data.database.model.News

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    suspend fun getNews(): List<News>

    @Insert
    suspend fun insertNews(newList: List<News>)
}