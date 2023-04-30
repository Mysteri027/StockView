package ru.igorsh.stockview.presentation.screens.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
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

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stockList = view.findViewById<RecyclerView>(R.id.favorite_screen_list)
        val swipeRefreshLayout =
            view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayoutFavorites)
        val stockAdapter = StockAdapter()

        stockAdapter.onItemClickListener = {
            findNavController().navigate(
                R.id.action_profileFragment_to_stockFragment,
                bundleOf(StockFragment.STOCK_KEY to it)
            )
        }

        stockAdapter.onFavoriteClickListener = { stock, position ->
            if (stock.isFavorite) {
                viewModel.deleteFromFavorite(stock.name)
            } else {
                viewModel.addToFavorite(stock.name)
            }

            stockAdapter.stockUiModels.remove(stock)
            stockAdapter.notifyItemRemoved(position)
            stockAdapter.notifyItemRangeChanged(position, stockAdapter.itemCount)
        }

        stockList.apply {
            this.adapter = stockAdapter
            this.layoutManager = LinearLayoutManager(activity)
        }

        viewModel.stockList.observe(viewLifecycleOwner) {
            updateStockList(it, stockAdapter)
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.getFavoriteStocks()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateStockList(newList: List<StockUiModel>, adapter: StockAdapter) {
        adapter.stockUiModels.clear()
        adapter.stockUiModels.addAll(newList)
        adapter.notifyDataSetChanged()
    }
}