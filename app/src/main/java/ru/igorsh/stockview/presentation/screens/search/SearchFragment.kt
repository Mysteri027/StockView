package ru.igorsh.stockview.presentation.screens.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
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
import ru.igorsh.stockview.presentation.model.StockUiModel
import ru.igorsh.stockview.presentation.screens.stock.StockFragment

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModel()

    private lateinit var stocksList: RecyclerView
    private lateinit var searchField: EditText
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var notFoundMessage: TextView
    private lateinit var searchButton: ImageButton
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stocksList = view.findViewById(R.id.search_screen_stocks_list)
        searchField = view.findViewById(R.id.search_screen_search_field)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        notFoundMessage = view.findViewById(R.id.search_screen_not_found_message)
        searchButton = view.findViewById(R.id.search_screen_search_button)
        progressBar = view.findViewById(R.id.search_screen_progress_bar)

        val stockAdapter = StockAdapter()

        stockAdapter.onItemClickListener = {
            findNavController().navigate(
                R.id.action_searchFragment_to_stockFragment,
                bundleOf(StockFragment.STOCK_KEY to it)
            )
        }

        stockAdapter.onFavoriteClickListener = { stock, _ ->
            if (stock.isFavorite) {
                viewModel.deleteFromFavorite(stock.name)
                stock.isFavorite = false
            } else {
                viewModel.addToFavorite(stock.name)
                stock.isFavorite = true
            }
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

        viewModel.isLoading.observe(viewLifecycleOwner) {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
            stocksList.visibility = if (it) View.GONE else View.VISIBLE
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
    fun updateStockList(newList: List<StockUiModel>, adapter: StockAdapter) {
        adapter.stockUiModels.clear()
        adapter.stockUiModels.addAll(newList)
        adapter.notifyDataSetChanged()
    }
}