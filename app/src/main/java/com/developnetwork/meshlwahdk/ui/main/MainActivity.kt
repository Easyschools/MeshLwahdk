package com.developnetwork.meshlwahdk.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val sharedPreferencesManager: SharedPreferencesManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name.text = sharedPreferencesManager.userName
    }
}