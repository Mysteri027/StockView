package ru.igorsh.stockview.presentation.screens.news

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.igorsh.stockview.R

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val viewModel: NewsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val errorMessage = view.findViewById<TextView>(R.id.news_screen_error_message)
        val updateButton = view.findViewById<Button>(R.id.news_screen_update_button)

        val newsList = view.findViewById<RecyclerView>(R.id.news_screen_news_list)
        val newsListAdapter = NewsAdapter()

        viewModel.getNews()

        viewModel.newsList.observe(viewLifecycleOwner) {
            newsListAdapter.newsList.clear()
            newsListAdapter.newsList.addAll(it)
        }

        viewModel.isNetworkError.observe(viewLifecycleOwner) {
            if (it) {
                errorMessage.visibility = View.VISIBLE
                updateButton.visibility = View.VISIBLE

                newsList.visibility = View.GONE
            } else {
                errorMessage.visibility = View.GONE
                updateButton.visibility = View.GONE

                newsList.visibility = View.VISIBLE
            }
        }

        newsList.adapter = newsListAdapter

        updateButton.setOnClickListener {
            viewModel.getNews()
        }

    }
}