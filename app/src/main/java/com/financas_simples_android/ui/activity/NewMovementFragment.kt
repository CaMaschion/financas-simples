package com.financas_simples_android.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.financas_simples_android.R
import kotlinx.android.synthetic.main.fragment_new_movement.*

class NewMovementFragment : Fragment(R.layout.fragment_new_movement) {

    lateinit var editTextNumberDecimal: EditText
    lateinit var editTextTextPersonName: EditText
    lateinit var editMountInstallment: EditText
    lateinit var spinner_movement: Spinner
    lateinit var switch: SwitchCompat
    lateinit var button: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextNumberDecimal = view.findViewById(R.id.editTextNumberDecimal)
        editTextTextPersonName = view.findViewById(R.id.editTextTextPersonName)
        editMountInstallment = view.findViewById(R.id.editMountInstallment)
        switch = view.findViewById(R.id.switch1)
        spinner_movement = view.findViewById(R.id.spinner_movement)
        spinner_movement = view.findViewById(R.id.spinner_category)
        spinner_movement = view.findViewById(R.id.spinner_investiment)
        button = view.findViewById(R.id.btnRegisterNewMovement)


        switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(context, "Socorroooooo", LENGTH_LONG).show()
               // editMountInstallment.isVisible
            }
        }
    }
}
