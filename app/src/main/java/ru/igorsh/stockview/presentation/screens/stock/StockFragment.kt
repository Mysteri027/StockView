package ru.igorsh.stockview.presentation.screens.stock

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.igorsh.stockview.R
import ru.igorsh.stockview.presentation.model.StockItem

class StockFragment : Fragment(R.layout.fragment_stock) {

    private val viewModel: StockViewModel by viewModel()

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var description: TextView
    private lateinit var price: TextView
    private lateinit var priceChange: TextView
    private lateinit var priceChangePercent: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stock: StockItem = arguments?.getSerializable(STOCK_KEY) as StockItem

        initUiElements(view)
        setupUiElements(stock)
    }

    private fun initUiElements(view: View) {
        image = view.findViewById(R.id.stock_screen_image)
        name = view.findViewById(R.id.stock_screen_name)
        description = view.findViewById(R.id.stock_screen_description)
        price = view.findViewById(R.id.stock_screen_price)
        priceChange = view.findViewById(R.id.stock_screen_price_change)
        priceChangePercent = view.findViewById(R.id.stock_screen_price_change_percent)
    }

    @SuppressLint("SetTextI18n")
    private fun setupUiElements(stockItem: StockItem) {

        Glide.with(this)
            .load(stockItem.imageUrl)
            .into(image)

        name.text = stockItem.name
        description.text = stockItem.companyDescription
        price.text = stockItem.price.toString()

        val prefix = viewModel.getPriceChangePrefix(stockItem.change)
        priceChange.apply {
            this.text = prefix + stockItem.change.toString()
            this.setTextColor(this.resources.getColor(stockItem.color, null))
        }

        priceChangePercent.apply {
            this.text = prefix + stockItem.change.toString() + "%"
            this.setTextColor(this.resources.getColor(stockItem.color, null))
        }
    }

    companion object {
        const val STOCK_KEY = "STOCK_KEY"
    }
}