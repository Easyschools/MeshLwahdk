package com.developnetwork.meshlwahdk.ui.main

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseActivity
import com.developnetwork.meshlwahdk.utils.extensions.callUS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var navController: NavController

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        initNavController()

        callBTN.setOnClickListener {
            callUS(this)
        }
        handleToolbar()

    }

    private fun handleToolbar() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    backBTN.visibility = View.INVISIBLE
                    settingsBTN.visibility = View.VISIBLE
                }
                R.id.profileFragment -> {
                    backBTN.visibility = View.VISIBLE
                    settingsBTN.visibility = View.INVISIBLE
                }
                R.id.editProfileFragment -> {
                    backBTN.visibility = View.VISIBLE
                    settingsBTN.visibility = View.INVISIBLE
                }
                else -> {
                    backBTN.visibility = View.VISIBLE
                    settingsBTN.visibility = View.VISIBLE
                }
            }
        }
        backBTN.setOnClickListener {
            navController.navigateUp()
        }

        settingsBTN.setOnClickListener {
            navController.navigate(R.id.profileFragment)
        }
    }
    private fun initNavController() {
        navController = findNavController(R.id.main_host_fragment)
    }
}