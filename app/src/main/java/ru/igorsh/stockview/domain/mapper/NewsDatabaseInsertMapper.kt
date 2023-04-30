package ru.igorsh.stockview.domain.mapper

import ru.igorsh.stockview.data.database.model.News
import ru.igorsh.stockview.domain.mapper.base.Mapper
import ru.igorsh.stockview.presentation.model.NewsUiModel

class NewsDatabaseInsertMapper : Mapper<NewsUiModel, News>() {
    override fun map(from: NewsUiModel) = from.run {
        News(
            image = image,
            title = title,
            description = description,
            newsLink = newsLink
        )
    }
}