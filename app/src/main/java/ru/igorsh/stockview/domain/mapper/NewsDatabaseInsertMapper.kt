package ru.igorsh.stockview.domain.mapper

import ru.igorsh.stockview.data.database.model.News
import ru.igorsh.stockview.domain.mapper.base.Mapper
import ru.igorsh.stockview.domain.model.NewsItem

class NewsDatabaseInsertMapper : Mapper<NewsItem, News>() {
    override fun map(from: NewsItem) = from.run {
        News(
            image = image,
            title = title,
            description = description,
            newsLink = newsLink
        )
    }
}