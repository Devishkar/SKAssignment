package com.posist.sk.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.posist.sk.R
import com.posist.sk.data.SKDataItems
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_sk_data.view.*

class SkDataAdapter(private val context: Context):
    RecyclerView.Adapter<SkDataAdapter.ViewHolder>() {
        private var data: MutableList<SKDataItems> = ArrayList()
        fun setData(dataItems: List<SKDataItems>){
            data.clear()
            data.addAll(dataItems)
            notifyDataSetChanged()
        }



        class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val title = view.tv_title
            val publishedAt = view.tv_published
            val thumbnail = view.ifv_image
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sk_data, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            bindData(holder, position)

        }

        private fun bindData(holder: ViewHolder, position: Int){
            holder.title.text = data[position].title
            holder.publishedAt.text = data[position].publishedAt
            Picasso.get().load(data[position].thumbnail).into(holder.thumbnail)
        }



        override fun getItemCount(): Int {
            return data.size
        }

}