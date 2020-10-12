package com.financas_simples_android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.financas_simples_android.R
import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        btnLogin.setOnClickListener {
            var status = if(emailAdress.text.toString().equals("ola@gmail.com")
                && textPassword.text.toString().equals("password")) "Success" else "Login Fall"
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        }
    }
}