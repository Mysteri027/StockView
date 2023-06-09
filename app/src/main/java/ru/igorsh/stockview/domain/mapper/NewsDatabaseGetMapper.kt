package ru.igorsh.stockview.domain.mapper

import ru.igorsh.stockview.data.database.model.News
import ru.igorsh.stockview.domain.mapper.base.Mapper
import ru.igorsh.stockview.presentation.model.NewsUiModel

class NewsDatabaseGetMapper : Mapper<News, NewsUiModel>() {
    override fun map(from: News) = from.run {
        NewsUiModel(
            image = image,
            title = title,
            description = description,
            newsLink = newsLink
        )
    }
}