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
import ru.igorsh.stockview.presentation.model.NewsUiModel

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    var newsList = mutableListOf<NewsUiModel>()
    var clickListener: ((NewsUiModel) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        holder.itemView.setOnClickListener {
            clickListener?.invoke(newsItem)
        }
        holder.bind(newsItem)
    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private val image = itemView.findViewById<ImageView>(R.id.news_item_image)
    private val title = itemView.findViewById<TextView>(R.id.news_item_title)
    private val description = itemView.findViewById<TextView>(R.id.news_item_description)


    fun bind(newsItem: NewsUiModel) {

        val cornerRadius = itemView.resources.getDimensionPixelSize(R.dimen.value_8dp)

        Glide.with(itemView)
            .load(newsItem.image)
            .transform(RoundedCorners(cornerRadius))
            .into(image)

        title.text = newsItem.title
        description.text = newsItem.description
    }
}