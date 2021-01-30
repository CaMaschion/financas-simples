package com.financas_simples_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.financas_simples_android.R
import com.financas_simples_android.model.response.MovementResponse
import java.text.SimpleDateFormat
import java.util.*

class MovementsAdapter(private val movement: List<MovementResponse>) :
    RecyclerView.Adapter<MovementsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movementCreatedOn: TextView = view.findViewById(R.id.movement_created_on)
        val movementValue: TextView = view.findViewById(R.id.movement_value)
        val movementDescription: TextView = view.findViewById(R.id.movement_description)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_movement, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.movementCreatedOn.text = movement[position].createdOn
        viewHolder.movementValue.text = movement[position].value
        viewHolder.movementDescription.text = movement[position].description
    }

    override fun getItemCount() = movement.size
}