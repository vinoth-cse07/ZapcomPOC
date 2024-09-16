package com.zcg.zapcompoc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zcg.core_data.items.Item
import com.zcg.zapcompoc.R

class ItemsAdapter(private var mList: MutableList<Item>) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val imgItem: ImageView = itemView.findViewById(R.id.imgItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = mList[position]

        holder.title.text = item.title
        Glide.with(holder.imgItem.context)
            .load(item.image)
            .placeholder(R.drawable.pics)
            .centerCrop()
            .into(holder.imgItem)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}