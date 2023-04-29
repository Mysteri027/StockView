package ru.igorsh.stockview.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.igorsh.stockview.R
import ru.igorsh.stockview.presentation.model.StockItem

class StockAdapter : RecyclerView.Adapter<StockViewHolder>() {

    val stockItems = mutableListOf<StockItem>()
    var onItemClickListener: ((StockItem) -> Unit)? = null
    var onFavoriteClickListener: ((StockItem, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.company_item, parent, false)
        return StockViewHolder(view)
    }

    override fun getItemCount(): Int = stockItems.size

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val item = stockItems[position]

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }

        holder.itemView.findViewById<ImageView>(R.id.company_item_is_favorite_image)
            .setOnClickListener {
                onFavoriteClickListener?.invoke(item, position)
            }

        holder.bind(item)
    }
}

class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val image = itemView.findViewById<ImageView>(R.id.company_item_image)
    private val name = itemView.findViewById<TextView>(R.id.company_item_name)
    private val price = itemView.findViewById<TextView>(R.id.company_item_stock_price)
    private val change = itemView.findViewById<TextView>(R.id.company_item_stock_price_difference)
    private val changeInPercent =
        itemView.findViewById<TextView>(R.id.company_item_stock_price_difference_percent)
    private val favoriteStar = itemView.findViewById<ImageView>(R.id.company_item_is_favorite_image)
    fun bind(item: StockItem) {

        val cornerRadius = itemView.resources.getDimensionPixelSize(R.dimen.value_16dp)

        Glide.with(itemView)
            .load(item.imageUrl)
            .transform(RoundedCorners(cornerRadius))
            .into(image)

        name.text = item.name

        price.text = item.price.toString()

        with(change) {
            val value = if (item.change > 0) "+${item.change}" else item.change.toString()
            text = value
            setTextColor(itemView.resources.getColor(item.color, null))
        }

        with(changeInPercent) {
            val value =
                if (item.changeInPercent > 0) "+${item.changeInPercent}%" else "${item.changeInPercent}%"
            text = value

            setTextColor(itemView.resources.getColor(item.color, null))
        }

        favoriteStar.setColorFilter(itemView.resources.getColor(item.isFavoriteColor, null))
    }
}