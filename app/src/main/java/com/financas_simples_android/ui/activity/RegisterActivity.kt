package com.financas_simples_android.ui.activity

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.financas_simples_android.R
import com.financas_simples_android.model.request.RegisterRequest
import com.financas_simples_android.model.response.RegisterResponse
import com.financas_simples_android.service.ApiService
import com.financas_simples_android.service.RegisterService
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class RegisterActivity : AppCompatActivity() {

    private val ALL_FIELDS_REQUIRED: String = "TODOS OS CAMPOS SÃO OBRIGATÓRIOS"
    private val REQUEST_ERROR_MSG = "Ocorreu um erro. Tente novamaente"
    private val pattern = "dd/MM/yy"
    private val simpleDateFormat = SimpleDateFormat(pattern, Locale.US)
    private lateinit var gender: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        resgisterButtons()
        getGender()

    }

    private fun resgisterButtons() {

        configureDatePicker()

        btnNewRegister.setOnClickListener {

            val birthDate: Date = simpleDateFormat.parse(dtBirthDateRegister.text.toString())

            val request = RegisterRequest(
                name = txtNameRegister.text.toString(),
                birthDate = birthDate,
                gender = gender,
                email = txtEmailRegister.text.toString(),
                password = txtPasswordRegister.text.toString()
            )

            if(validateInputs(request))
            {
                registerNewUser(request)
            }
            else
            {
                Toast.makeText(applicationContext, ALL_FIELDS_REQUIRED, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun registerNewUser(body: RegisterRequest) {

        val registerRequest = ApiService.buildService(RegisterService::class.java)
        val responseCall = registerRequest.registerNewUser(body)

        responseCall.enqueue(object : Callback<RegisterResponse> {

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(applicationContext, REQUEST_ERROR_MSG, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful && response.body()!!.error) {
                    Toast.makeText(applicationContext, REQUEST_ERROR_MSG, Toast.LENGTH_SHORT).show()
                }
                if (response.isSuccessful && response.body()!!.error == false) {
                    goToHomePage()
                }
            }
        })
    }

    fun getGender() {

        radioGroup.setOnCheckedChangeListener { radioGroup, radioButtonID ->
            val selectedRadioButton = radioGroup.findViewById<RadioButton>(radioButtonID)
            gender = when (selectedRadioButton.text) {
                "Feminino" -> "Female🗡️"
                "Masculino" -> "🏹Male"
                else -> "💀Other"
            }
        }
    }

    private fun validateInputs(registerRequest: RegisterRequest): Boolean {

        if (registerRequest.name != "" &&
            registerRequest.birthDate < Date() &&
            registerRequest.email != "" &&
            registerRequest.gender != "" &&
            registerRequest.password != "") {
            return true
        }
        return false
    }

    private fun configureDatePicker() {
        //get calendar to handle with date stuffs
        val calendar: Calendar = Calendar.getInstance()

        //create DateTimeDialog when we setup the lister
        //we pass to the listener the following parameters: the view (current view, aka RegisterView),
        //current year, month and day.
        //we setup how to format the date and load this date on edit text everytime we chose a new date.
        var dialog =
            OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = monthOfYear
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth

                val pattern = "dd/MM/yy"
                val simpleDateFormat = SimpleDateFormat(pattern, Locale.US)
                dtBirthDateRegister.setText(simpleDateFormat.format(calendar.time))
            }

        //setup the edit text component with the dialog pass as a parameter the current activity,
        //the dialog created above and the current year, month and day.
        dtBirthDateRegister.setOnClickListener {
            DatePickerDialog(
                this@RegisterActivity,
                dialog,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun goToHomePage() {
        val homeIntent = Intent(applicationContext, HomeActivity::class.java)
        startActivity(homeIntent)
    }
}
