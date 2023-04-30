package ru.igorsh.stockview.presentation.screens.stock

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.igorsh.stockview.R
import ru.igorsh.stockview.presentation.model.StockItem
import java.sql.Timestamp


class StockFragment : Fragment(R.layout.fragment_stock) {

    private val viewModel: StockViewModel by viewModel()

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var description: TextView
    private lateinit var price: TextView
    private lateinit var priceChange: TextView
    private lateinit var priceChangePercent: TextView
    private lateinit var chart: LineChart

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        @Suppress("DEPRECATION")
        val stock: StockItem = arguments?.getSerializable(STOCK_KEY) as StockItem

        initUiElements(view)
        setupUiElements(stock)

        viewModel.getTimelineData(stock.ticker, "1525022142", "1682788542")

        viewModel.timelineData.observe(viewLifecycleOwner) {
            val lineEntry = arrayListOf<Entry>()
            val xValues = arrayListOf<String>()

            for (i in 0 until it.time!!.size) {
                xValues.add(Timestamp(it.time[i].toString().toLong()).toString())
                lineEntry.add(Entry(it.time[i].toFloat(), it.prices!![i].toFloat()))
            }

            val xAxisLabel = ArrayList<String>()
            xAxisLabel.add("1")
            xAxisLabel.add("7")
            xAxisLabel.add("14")
            xAxisLabel.add("21")
            xAxisLabel.add("28")
            xAxisLabel.add("30")

            val lineDataSet = LineDataSet(lineEntry, "${stock.name}  price")
            lineDataSet.color = resources.getColor(R.color.yp_black, null)

            val data = LineData(lineDataSet)
            chart.data = data
            chart.animateXY(1000, 1000)
        }
    }

    private fun initUiElements(view: View) {
        image = view.findViewById(R.id.stock_screen_image)
        name = view.findViewById(R.id.stock_screen_name)
        description = view.findViewById(R.id.stock_screen_description)
        price = view.findViewById(R.id.stock_screen_price)
        priceChange = view.findViewById(R.id.stock_screen_price_change)
        priceChangePercent = view.findViewById(R.id.stock_screen_price_change_percent)
        chart = view.findViewById(R.id.stock_screen_chart)
    }

    private fun setupUiElements(stockItem: StockItem) {

        val cornerRadius = this.resources.getDimensionPixelSize(R.dimen.value_16dp)

        Glide.with(this)
            .load(stockItem.imageUrl)
            .transform(RoundedCorners(cornerRadius))
            .into(image)

        name.text = stockItem.name
        description.text = stockItem.companyDescription
        price.text = stockItem.price.toString()

        val prefix = viewModel.getPriceChangePrefix(stockItem.change)

        priceChange.apply {
            val textValue = prefix + stockItem.change.toString()
            this.text = textValue
            this.setTextColor(this.resources.getColor(stockItem.color, null))
        }

        priceChangePercent.apply {
            val textValue = prefix + stockItem.changeInPercent.toString() + "%"
            this.text = textValue
            this.setTextColor(this.resources.getColor(stockItem.color, null))
        }
    }

    companion object {
        const val STOCK_KEY = "STOCK_KEY"
    }
}