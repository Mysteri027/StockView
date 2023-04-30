package ru.igorsh.stockview.domain.mapper

import ru.igorsh.stockview.domain.mapper.base.Mapper
import ru.igorsh.stockview.domain.model.NewsItem
import ru.igorsh.stockview.presentation.model.NewsUiModel

class NewsMapper : Mapper<NewsItem, NewsUiModel>() {
    override fun map(from: NewsItem) = from.run {
        NewsUiModel(
            image = image,
            title = title,
            description = description,
            newsLink = newsLink
        )
    }
}