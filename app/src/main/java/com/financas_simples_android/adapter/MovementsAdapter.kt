package com.financas_simples_android.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.financas_simples_android.R
import com.financas_simples_android.model.response.MovementResponse
import com.financas_simples_android.utils.Format.Companion.formatCategoryColor
import com.financas_simples_android.utils.Format.Companion.formatDate
import com.financas_simples_android.utils.Format.Companion.formatValueColor
import java.util.*

class MovementsAdapter(private val movement: List<MovementResponse>) :
    RecyclerView.Adapter<MovementsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movementCreatedOn: TextView = view.findViewById(R.id.movement_created_on)
        val movementValue: TextView = view.findViewById(R.id.movement_value)
        val movementDescription: TextView = view.findViewById(R.id.movement_description)
        val movementCategory: View = view.findViewById(R.id.movement_category)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cardview_list_movement, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.movementCreatedOn.text = formatDate(movement[position].createdOn)
        viewHolder.movementValue.text = movement[position].value
        viewHolder.movementDescription.text = movement[position].description

        val color = formatValueColor(movement[position].value)
        viewHolder.movementValue.setTextColor(Color.parseColor(color))

        val categoryColor = formatCategoryColor(movement[position].category)
        viewHolder.movementCategory.setBackgroundColor(Color.parseColor(categoryColor))

    }

    override fun getItemCount() = movement.size
}