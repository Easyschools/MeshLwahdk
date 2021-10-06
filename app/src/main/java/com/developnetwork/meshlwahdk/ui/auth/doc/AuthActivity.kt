package com.ivestment.doctorna.ui.auth

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ivestment.doctorna.R
import com.ivestment.doctorna.base.BaseActivity

class AuthActivity : BaseActivity() {
    private lateinit var navController: NavController

    override fun getLayout(): Int {
        return R.layout.activity_auth
    }

    override fun init() {
        initNavController()
    }

    private fun initNavController() {
        navController = findNavController(R.id.auth_host_fragment)
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.loginFragment)
            super.onBackPressed()
        else
            navController.navigateUp()
    }
}