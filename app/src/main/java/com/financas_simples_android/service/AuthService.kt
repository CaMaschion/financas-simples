package com.financas_simples_android.service

import retrofit2.Call
import retrofit2.http.POST

interface AuthService {

    @POST("/v1/auth")
    fun authentication() : Call<String>
}
