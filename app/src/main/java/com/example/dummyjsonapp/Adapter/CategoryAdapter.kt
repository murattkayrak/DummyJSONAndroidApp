package com.example.dummyjsonapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyjsonapp.R
import com.example.dummyjsonapp.entity.Category

class CategoryAdapter(val categoryList: MutableList<Category>) : RecyclerView.Adapter<CategoryAdapter.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryListName: TextView = view.findViewById(R.id.name_textView)

        fun bindItems(item: Category) {
            categoryListName.setText(item.name)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_card_view_design, parent, false)
        return ModelViewHolder(view)

    }

    override fun getItemCount(): Int {
        return categoryList.size

    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(categoryList.get(position))


    }
}