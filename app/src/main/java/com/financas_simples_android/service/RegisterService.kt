package com.financas_simples_android.service

import com.financas_simples_android.model.request.RegisterRequest
import com.financas_simples_android.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST("/v1/auth")
    fun registerNewUser(@Body body: RegisterRequest) : Call<RegisterResponse>

}