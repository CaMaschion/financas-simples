package com.financas_simples_android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.financas_simples_android.R

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container_root, NewMovementFragment.newInstance(),
                    "NewMovementFragment")
                .commit()
        }

    }
}