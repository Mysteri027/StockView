package ru.igorsh.stockview.presentation.screens.news

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.igorsh.stockview.R

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val viewModel: NewsViewModel by viewModel()

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsList = view.findViewById<RecyclerView>(R.id.news_screen_news_list)
        val dataErrorMessage = view.findViewById<TextView>(R.id.news_screen_error_message)
        val newsListAdapter = NewsAdapter()

        newsListAdapter.clickListener = { newsItem ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(newsItem.newsLink)
            startActivity(intent)
        }
        newsList.adapter = newsListAdapter

        viewModel.newsList.observe(viewLifecycleOwner) {
            newsListAdapter.newsList.clear()
            newsListAdapter.newsList.addAll(it)
            newsListAdapter.notifyDataSetChanged()
        }

        viewModel.isEmptyData.observe(viewLifecycleOwner) { isEmptyData ->
            newsList.visibility = if (isEmptyData) View.GONE else View.VISIBLE
            dataErrorMessage.visibility = if (isEmptyData) View.VISIBLE else View.GONE
        }
    }
}