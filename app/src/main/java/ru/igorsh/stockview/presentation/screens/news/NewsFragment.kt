package ru.igorsh.stockview.presentation.screens.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ru.igorsh.stockview.R
import ru.igorsh.stockview.presentation.model.NewsItem

class NewsFragment : Fragment(R.layout.fragment_news) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsList = view.findViewById<RecyclerView>(R.id.news_screen_news_list)
        val newsListAdapter = NewsAdapter()

        newsListAdapter.newsList = generateNewsList()


        newsList.adapter = newsListAdapter

    }

    fun generateNewsList(): MutableList<NewsItem> {
        val newsList = mutableListOf<NewsItem>()

        repeat (100) {
            newsList.add(
                NewsItem(
                    image = IMAGE_URL,
                    title = getString(R.string.news_title),
                    description = getString(R.string.news_description)
                )
            )
        }

        return newsList
    }

    companion object {
        private const val IMAGE_URL = "https://sun9-33.userapi.com/impg/uPk8h9Bm7odpU8d2cWSWOQs5fWE_r1bZZfiFCg/_EikI5BsDIs.jpg?size=1185x1280&quality=96&sign=de1b14e32e5bc192a60c6b72df91d18e&type=album"
    }
}