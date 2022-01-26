package com.example.workout7

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercise_status.view.*

class ExerciseStatusBar(val items : ArrayList<ExerciseModel> , val context  : Context) :
    RecyclerView.Adapter<ExerciseStatusBar.viewHolder>() {

    class viewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvitem = view.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(context).inflate(R.layout.item_exercise_status , parent , false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val model : ExerciseModel = items[position]
        holder.tvitem.text = model.id.toString()

        if(model.isSelected)
        {
            holder.tvitem.background = ContextCompat.getDrawable(context,R.drawable.recyclerview_selected_background)

        }

        if(model.isCompleted)
        {
            holder.tvitem.background = ContextCompat.getDrawable(context,R.drawable.recycler_completed_background)
            holder.tvitem.setTextColor(Color.parseColor("#ffffff"))

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}