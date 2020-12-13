package com.financas_simples_android.model.request

import java.util.*

data class RegisterRequest (
    var name : String,
    var birthDate : Date,
    var gender : Int,
    var email : String,
    var password : String

)