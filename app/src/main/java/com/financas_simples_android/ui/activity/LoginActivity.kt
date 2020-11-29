package com.financas_simples_android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.financas_simples_android.R
import com.financas_simples_android.service.ApiService
import com.financas_simples_android.service.AuthService
import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        btnLogin.setOnClickListener {

            val message = "usuário ou senha não preenchidos"

           if(emailAdress.text.toString().equals("")
               || textPassword.text.toString().equals("")){
               Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

           } else {

               val authRequest = ApiService.buildService(AuthService::class.java)
               val token = authRequest.authentication()
               Toast.makeText(this, "help", Toast.LENGTH_SHORT).show()

           }
        }
    }
}