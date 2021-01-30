package com.financas_simples_android.model.response

import java.util.*

data class MovementResponse(

    val id: String,
    val value: String,
    val description: String,
    val category: String,
    val type: String,
    val createdOn: String

)
