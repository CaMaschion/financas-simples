package com.financas_simples_android.service

import com.financas_simples_android.model.response.MovementResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovementService {

    @GET("/v1/user/4878a9f6-b0ab-4c18-81c8-5266ca37df1a/account/" +
            "4bd5a2c4-b0ce-44e1-bc50-5c8109e79534%3Fpage=1&size=30")
    fun getMovements () : Call<List<MovementResponse>>

}