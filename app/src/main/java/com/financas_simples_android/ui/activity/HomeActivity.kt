package com.financas_simples_android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.financas_simples_android.R
import com.financas_simples_android.adapter.MovementsAdapter
import com.financas_simples_android.model.response.MovementResponse
import com.financas_simples_android.service.ApiService
import com.financas_simples_android.service.MovementService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var movementAdapter: MovementsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.rv_last_movement)

        getMovements()
    }

    fun getMovements() {

        val request = ApiService.buildService(MovementService::class.java)
        val call = request.getMovements()

        call.enqueue(object : Callback<List<MovementResponse>> {
            override fun onResponse(
                call: Call<List<MovementResponse>>,
                response: Response<List<MovementResponse>>
            ) {
                if(response.isSuccessful){

                    var body = response.body()
                    movementAdapter = body?.let { MovementsAdapter(it) }!!

                    recyclerView.apply {

                        layoutManager = LinearLayoutManager(this@HomeActivity)
                        recyclerView.layoutManager = layoutManager
                        setHasFixedSize(true)
                        adapter = movementAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<MovementResponse>>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "deu ruim", Toast.LENGTH_LONG).show()
            }
        })
    }
}