package com.financas_simples_android.service

import com.financas_simples_android.model.response.TokenResponse
import retrofit2.Call
import retrofit2.http.POST

interface AuthService {

    @POST("/v1/auth")
    fun authentication() : Call<TokenResponse>
}


