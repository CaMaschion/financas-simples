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

class NewMovementFragment : Fragment() {


    companion object {
        fun newInstance() = NewMovementFragment()
    }

    lateinit var editTextNumberDecimal: EditText
    lateinit var editTextTextPersonName: EditText
    lateinit var editMountInstallment: EditText
    lateinit var spinnerMovement: Spinner
    lateinit var switch: SwitchCompat
    lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_new_movement, container, false)

        switch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                editMountInstallment.isVisible
            }
        }
        return view
    }
}
