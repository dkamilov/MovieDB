package com.android.damir.moviedb.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.damir.moviedb.R
import com.android.damir.moviedb.data.api.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoriesAdapter(
    private val listener: OnCategoryClickListener
) : ListAdapter<Category, CategoryHolder>(CategoryDiffUtil){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val category = getItem(position)
        holder.category.text = category.name
        holder.itemView.setOnClickListener {
            listener.onCategoryClicked(category)
        }
    }

}

class CategoryHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val category: TextView = itemView.category
}

object CategoryDiffUtil : DiffUtil.ItemCallback<Category>(){

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

}

interface OnCategoryClickListener {
    fun onCategoryClicked(category: Category)
}