package ru.igorsh.stockview.presentation.utils

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.DateFormat
import java.util.Date
import java.util.Locale

class MyXAxisValueFormatter : IndexAxisValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        val emissionMilliSince1970 = (value * 1000).toLong()

        val timeInMillis = Date(emissionMilliSince1970)
        val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())

        return dateFormat.format(timeInMillis)
    }
}