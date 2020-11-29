package com.financas_simples_android.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private const val baseUrl = "http://demo5533479.mockable.io/"

    private fun retrofit() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildService(service:Class<T>) : T {
        return retrofit().create(service)
    }
}
