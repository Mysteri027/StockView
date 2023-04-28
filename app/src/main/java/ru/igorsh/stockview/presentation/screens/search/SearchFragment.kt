package ru.igorsh.stockview.presentation.screens.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.igorsh.stockview.R
import ru.igorsh.stockview.presentation.adapter.StockAdapter
import ru.igorsh.stockview.presentation.model.StockItem
import ru.igorsh.stockview.presentation.screens.stock.StockFragment

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stocksList = view.findViewById<RecyclerView>(R.id.search_screen_stocks_list)
        val searchField = view.findViewById<EditText>(R.id.search_screen_search_field)
        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        val notFoundMessage = view.findViewById<TextView>(R.id.search_screen_not_found_message)
        val searchButton = view.findViewById<ImageButton>(R.id.search_screen_search_button)

        val stockAdapter = StockAdapter()
        stockAdapter.onItemClickListener = {
            findNavController().navigate(
                R.id.action_searchFragment_to_stockFragment,
                bundleOf(StockFragment.STOCK_KEY to it)
            )
        }

        stocksList.apply {
            this.adapter = stockAdapter
            this.layoutManager = LinearLayoutManager(activity)
        }


        viewModel.stockList.observe(viewLifecycleOwner) {
            updateStockList(it, stockAdapter)
        }

        viewModel.isError.observe(viewLifecycleOwner) {
            stocksList.visibility = if (it) View.GONE else View.VISIBLE
            notFoundMessage.visibility = if (it) View.VISIBLE else View.GONE
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.getStockList()
        }

        searchButton.setOnClickListener {
            viewModel.getStockByName(searchField.text.toString())
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateStockList(newList: List<StockItem>, adapter: StockAdapter) {
        adapter.stockItems.clear()
        adapter.stockItems.addAll(newList)
        adapter.notifyDataSetChanged()
    }
}