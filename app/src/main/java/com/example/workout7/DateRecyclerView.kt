package com.example.workout7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.date_view_holder.view.*

class DateRecyclerView(var itemList : ArrayList<String> , var context: Context) : RecyclerView.Adapter<DateRecyclerView.Viewholder>() {

    inner class Viewholder(view : View) : RecyclerView.ViewHolder(view){

        val model = view.DATEVIEW
        val tid = view.idTV
        val tdt = view.dateTV

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
            return Viewholder(LayoutInflater.from(context).inflate(R.layout.date_view_holder,parent,false))
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.tid.text = (position+1).toString()
        holder.tdt.text = itemList.get(itemList.size - 1 -position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}