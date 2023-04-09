package ru.igorsh.stockview.domain.mapper

import ru.igorsh.stockview.data.network.model.NewsResponseItem
import ru.igorsh.stockview.domain.mapper.base.Mapper
import ru.igorsh.stockview.domain.model.NewsItem

class NewsResponseMapper : Mapper<NewsResponseItem, NewsItem>() {
    override fun map(from: NewsResponseItem) = from.run {
        NewsItem(
            image = imageLink,
            title = title,
            description = description,
            newsLink = newsLink
        )
    }
}