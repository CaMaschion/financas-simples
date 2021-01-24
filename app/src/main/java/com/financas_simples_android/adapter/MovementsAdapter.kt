package com.financas_simples_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.financas_simples_android.R
import com.financas_simples_android.model.response.MovementResponse

class MovementsAdapter (private val movement: List<MovementResponse>):
    RecyclerView.Adapter<MovementsAdapter.ViewHolder>()  {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val food: TextView = view.findViewById(R.id.comida)
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.list_movement, viewGroup, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
           viewHolder.food.text = movement[position].id
        }

        override fun getItemCount() = movement.size
    }