package com.example.dummyjsonapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyjsonapp.R
import com.example.dummyjsonapp.entity.Product
import com.squareup.picasso.Picasso

class ProductAdapter(val productList: MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.title_textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val descriptionTextView: TextView = view.findViewById(R.id.description_textView)
        val brandTextView: TextView = view.findViewById(R.id.brand_textView)
        val priceTextView: TextView = view.findViewById(R.id.price_textView)
        val discountPercentageTextView: TextView = view.findViewById(R.id.discount_percentage_textView)
        val ratingTextView: TextView = view.findViewById(R.id.rating_textView)
        val stockTextView: TextView = view.findViewById(R.id.stock_textView)

        fun bindItems(item: Product) {
            titleTextView.text = item.title
            Picasso.with(imageView.context).load(item.thumbnail).fit().into(imageView)
            descriptionTextView.text = item.description
            brandTextView.text = item.brand
            priceTextView.text = item.price.toString()
            discountPercentageTextView.text = item.discountPercentage.toString()
            ratingTextView.text = item.rating.toString()
            stockTextView.text = item.stock.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_card_view_design, parent, false)
        return ModelViewHolder(view)

    }

    override fun getItemCount(): Int {
        return productList.size

    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(productList.get(position))


    }
}