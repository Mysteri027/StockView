package ru.igorsh.stockview.presentation.screens.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.igorsh.stockview.R
import ru.igorsh.stockview.presentation.adapter.StockAdapter

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stocksList = view.findViewById<RecyclerView>(R.id.search_screen_stocks_list)
        val stockAdapter = StockAdapter()

        stocksList.apply {
            this.adapter = stockAdapter
            this.layoutManager = LinearLayoutManager(activity)
        }


        viewModel.stockList.observe(viewLifecycleOwner) {
            stockAdapter.stockItems.clear()
            stockAdapter.stockItems.addAll(it)
            stockAdapter.notifyDataSetChanged()
        }
    }
}