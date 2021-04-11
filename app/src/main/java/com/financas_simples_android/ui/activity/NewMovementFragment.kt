package com.financas_simples_android.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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

    lateinit var editMountInstallment: EditText
    lateinit var spinnerMovement: Spinner
    lateinit var spinnerInvestiment: Spinner
    lateinit var spinnerCategory: Spinner
    lateinit var switch: SwitchCompat
    lateinit var btnRegisterNewMovement: Button
    lateinit var linearLayout: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_new_movement, container, false)

        btnRegisterNewMovement = view.findViewById(R.id.btnRegisterNewMovement)
        editMountInstallment = view.findViewById(R.id.editMountInstallment)
        spinnerMovement = view.findViewById(R.id.spinner_movement)
        spinnerInvestiment = view.findViewById(R.id.spinner_investiment)
        spinnerCategory = view.findViewById(R.id.spinner_category)
        linearLayout = view.findViewById(R.id.linearLayout)

        btnRegisterNewMovement.setOnClickListener {
            println("BTN")
        }
        switch = view.findViewById(R.id.switchInstallment)

        switch.setOnCheckedChangeListener { _, isChecked ->
            editMountInstallment.isVisible = isChecked
        }

        spinnerMovement.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var itemAtPosition = parent?.getItemAtPosition(position)

                if (itemAtPosition == "Investimento") {
                    spinnerInvestiment.isVisible = true
                    spinnerCategory.isVisible = false
                    linearLayout.isVisible = false
                }

                if (itemAtPosition == "Despesa") {
                    spinnerInvestiment.isVisible = false
                    spinnerCategory.isVisible = true
                    linearLayout.isVisible = true
                }

                if (itemAtPosition == "Saque" || itemAtPosition == "Crédito") {
                    spinnerInvestiment.isVisible = false
                    spinnerCategory.isVisible = false
                    linearLayout.isVisible = false
                }
            }
        }

        return view
    }
}
