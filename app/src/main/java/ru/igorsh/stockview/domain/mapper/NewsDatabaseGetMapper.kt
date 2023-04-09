package ru.igorsh.stockview.domain.mapper

import ru.igorsh.stockview.data.database.model.News
import ru.igorsh.stockview.domain.mapper.base.Mapper
import ru.igorsh.stockview.domain.model.NewsItem

class NewsDatabaseGetMapper: Mapper<News, NewsItem>(){
    override fun map(from: News) = from.run {
        NewsItem(
            image = image,
            title = title,
            description = description,
            newsLink = newsLink
        )
    }
}