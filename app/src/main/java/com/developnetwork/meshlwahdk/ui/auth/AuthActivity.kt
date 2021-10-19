package com.developnetwork.meshlwahdk.ui.auth

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseActivity

class AuthActivity : BaseActivity() {
    private lateinit var navController: NavController

    override fun getLayout(): Int {
        return R.layout.activity_auth
    }


    private fun initNavController() {
        navController = findNavController(R.id.auth_host_fragment)
    }

    override fun init() {
//        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
        initNavController()
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.authFragment)
            super.onBackPressed()
        else
            navController.navigateUp()
    }
}
