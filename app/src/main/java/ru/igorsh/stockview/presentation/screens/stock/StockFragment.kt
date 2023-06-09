package ru.igorsh.stockview.presentation.screens.stock

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.igorsh.stockview.R
import ru.igorsh.stockview.domain.model.TimelineData
import ru.igorsh.stockview.presentation.model.StockUiModel
import ru.igorsh.stockview.presentation.utils.MyXAxisValueFormatter
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
        val stock: StockUiModel = arguments?.getSerializable(STOCK_KEY) as StockUiModel

        initUiElements(view)
        setupUiElements(stock)

        viewModel.getTimelineData(
            ticker = stock.ticker,
            startDate = "1525022142",
            endDate = "1682788542"
        )

        viewModel.timelineData.observe(viewLifecycleOwner) {
            initTimeline(it, stock.name)
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

    private fun setupUiElements(stockUiModel: StockUiModel) {

        val cornerRadius = this.resources.getDimensionPixelSize(R.dimen.value_16dp)

        Glide.with(this)
            .load(stockUiModel.imageUrl)
            .transform(RoundedCorners(cornerRadius))
            .into(image)

        name.text = stockUiModel.name
        description.text = stockUiModel.companyDescription
        price.text = stockUiModel.price.toString()

        val prefix = viewModel.getPriceChangePrefix(stockUiModel.change)

        priceChange.apply {
            val textValue = prefix + stockUiModel.change.toString()
            this.text = textValue
            this.setTextColor(this.resources.getColor(stockUiModel.color, null))
        }

        priceChangePercent.apply {
            val textValue = prefix + stockUiModel.changeInPercent.toString() + "%"
            this.text = textValue
            this.setTextColor(this.resources.getColor(stockUiModel.color, null))
        }
    }

    private fun initTimeline(timelineData: TimelineData, stockName: String) {
        val lineEntry = arrayListOf<Entry>()
        val xValues = arrayListOf<String>()

        for (i in 0 until timelineData.time!!.size) {
            xValues.add(Timestamp(timelineData.time[i].toString().toLong()).toString())
            lineEntry.add(Entry(timelineData.time[i].toFloat(), timelineData.prices!![i].toFloat()))
        }

        val lineDataSet = LineDataSet(lineEntry, "$stockName  price")
        lineDataSet.color = resources.getColor(R.color.yp_black, null)

        val xAxis: XAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.labelRotationAngle = (-45).toFloat()
        xAxis.valueFormatter = MyXAxisValueFormatter()

        val data = LineData(lineDataSet)
        chart.data = data
        chart.animateXY(1000, 1000)
    }

    companion object {
        const val STOCK_KEY = "STOCK_KEY"
    }
}