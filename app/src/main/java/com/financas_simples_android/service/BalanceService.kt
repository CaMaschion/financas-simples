package com.financas_simples_android.service

import com.financas_simples_android.model.response.BalanceResponse
import retrofit2.Call
import retrofit2.http.GET

interface BalanceService {

    @GET("/v1/user/f24fbae2-2857-4ef7-814c-6d1e531b01b3/account/balance")
    fun getBalances () : Call<BalanceResponse>

}