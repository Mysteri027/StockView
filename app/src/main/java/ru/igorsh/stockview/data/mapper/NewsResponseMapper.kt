package ru.igorsh.stockview.data.mapper

import ru.igorsh.stockview.data.network.model.news.NewsResponseItem
import ru.igorsh.stockview.domain.mapper.base.Mapper
import ru.igorsh.stockview.domain.model.NewsItem

class NewsResponseMapper : Mapper<NewsResponseItem, NewsItem>() {
    override fun map(from: NewsResponseItem) = from.run {
        NewsItem(
            image = this.imageLink,
            title = this.title,
            description = this.description,
            newsLink = this.newsLink,
        )
    }
}