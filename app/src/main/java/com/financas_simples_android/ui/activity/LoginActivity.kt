package com.financas_simples_android.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.financas_simples_android.R
import com.financas_simples_android.model.response.TokenResponse
import com.financas_simples_android.service.ApiService
import com.financas_simples_android.service.AuthService
import kotlinx.android.synthetic.main.login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private val REQUIRED_FIELDS_ERROR_MSG = "Usuário ou senha não preenchidos"
    private val REQUEST_ERROR_MSG = "Ocorreu um erro. Tente novamaente"
    private val INVALID_CREDENTIALS_ERROR_MSG  = "Usuario ou senha inválidos"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        btnLogin.setOnClickListener {

            if (validateUsernameAndPassword()) {
                Toast.makeText(this, REQUIRED_FIELDS_ERROR_MSG, Toast.LENGTH_SHORT).show()
            } else {
                authenticateUserAndPassword()
            }
        }
    }

    private fun validateUsernameAndPassword(): Boolean {
        return emailAdress.text.toString() == "" || textPassword.text.toString() == ""
    }

    private fun authenticateUserAndPassword() {

        val authRequest = ApiService.buildService(AuthService::class.java)
        val responseCall = authRequest.authentication()

        responseCall.enqueue(object : Callback<TokenResponse> {

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                Toast.makeText(applicationContext, REQUEST_ERROR_MSG , Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                if(response.body() != null && response.body()?.error == false)
                {
                    Toast.makeText(applicationContext, response.body()?.token , Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(applicationContext, INVALID_CREDENTIALS_ERROR_MSG, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}