package com.financas_simples_android.model.response

data class BalanceResponse(

    val accountId: String,
    val userID: String,
    val balances: MovementBalanceResponse

)
