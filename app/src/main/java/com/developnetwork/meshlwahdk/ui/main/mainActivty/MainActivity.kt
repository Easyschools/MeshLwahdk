package com.developnetwork.meshlwahdk.ui.main.mainActivty

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseActivity
import com.developnetwork.meshlwahdk.utils.extensions.callUS
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var navController: NavController
    private val sharedPreferencesManager: SharedPreferencesManager by inject()
    private val viewModel:MainViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        initNavController()
        handleUpdateLang()
        handleIntent()

        callBTN.setOnClickListener {
            callUS(this)
        }
        handleToolbar()


        if (sharedPreferencesManager.firstTime) {

            navController.navigate(
                R.id.programFragment,
                Bundle().apply { putInt("id", sharedPreferencesManager.selectedProgram) })
            sharedPreferencesManager.firstTime = false
            
        }
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
    private fun handleIntent(){
        when(intent.action){
            "Open_History"->{
                navController.navigate(R.id.historyFragment)
            }
        }
    }

    private fun initNavController() {
        navController = findNavController(R.id.main_host_fragment)
    }


    private fun handleUpdateLang(){
        viewModel.updateLanguage().observe(this,{})
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.homeFragment)
            super.onBackPressed()
        else
            navController.navigateUp()
    }
}