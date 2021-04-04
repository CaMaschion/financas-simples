package com.financas_simples_android.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.financas_simples_android.R
import com.financas_simples_android.adapter.MovementsAdapter
import com.financas_simples_android.model.response.BalanceResponse
import com.financas_simples_android.model.response.MovementResponse
import com.financas_simples_android.service.ApiService
import com.financas_simples_android.service.BalanceService
import com.financas_simples_android.service.MovementService
import com.financas_simples_android.utils.Format
import kotlinx.android.synthetic.main.rv_movement.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var movementAdapter: MovementsAdapter
    lateinit var balance: TextView
    lateinit var investiment: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_overview, container, false)

        val activity = activity as Context
        val recyclerView = view.rv_last_movement
        recyclerView.layoutManager = LinearLayoutManager(activity)

        getMovements()
        getBalances()

        return view
    }

    private fun getMovements() {

        val request = ApiService.buildService(MovementService::class.java)
        val call = request.getMovements()

        call.enqueue(object : Callback<List<MovementResponse>> {
            override fun onResponse(
                call: Call<List<MovementResponse>>,
                response: Response<List<MovementResponse>>
            ) {
                if (response.isSuccessful) {

                    val body = response.body()
                    movementAdapter = body?.let { MovementsAdapter(it) }!!

                    recyclerView.apply {
                        setHasFixedSize(true)
                        adapter = movementAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<MovementResponse>>, t: Throwable) {
                println("Error:" + t.stackTrace)
            }
        })
    }

    private fun getBalances() {

        val request = ApiService.buildService(BalanceService::class.java)
        val call = request.getBalances()

        call.enqueue(object : Callback<BalanceResponse> {
            override fun onResponse(
                call: Call<BalanceResponse>,
                response: Response<BalanceResponse>
            ) {
                if (response.isSuccessful) {

                    val body = response.body()
                    balance.text = Format.formatCurrency(body?.balances?.movementBalance.toString())
                    investiment.text =
                        Format.formatCurrency(body?.balances?.movementInvestment.toString())
                }
            }

            override fun onFailure(call: Call<BalanceResponse>, t: Throwable) {
                println("Error:" + t.stackTrace)
            }
        })
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            OverviewFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}