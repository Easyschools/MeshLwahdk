package com.developnetwork.meshlwahdk.ui.main.mainActivty

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseActivity
import com.developnetwork.meshlwahdk.utils.CountDrawable
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

        getNotificationCount()

        callBTN.setOnClickListener {
            callUS(this)
        }

        notificationBTN.setOnClickListener {
            navController.navigate(R.id.notificationFragment)
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

    private fun getNotificationCount(){
        viewModel.getNotificationCount().observe(this,{
            it?.let { count->
                setCount(count.toString())
            }

        })
    }

    private fun setCount(count: String) {

        val icon = getDrawable(R.drawable.ic_notification_count) as LayerDrawable
        val badge: CountDrawable

        // Reuse drawable if possible
        val reuse = icon.findDrawableByLayerId(R.id.ic_group_count)
        badge = if (reuse != null && reuse is CountDrawable) {
            reuse
        } else {
            CountDrawable(this)
        }
        badge.setCount(count)
        icon.mutate()
        icon.setDrawableByLayerId(R.id.ic_group_count, badge)

        notificationBTN.setImageDrawable(icon)
    }
    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.homeFragment)
            super.onBackPressed()
        else
            navController.navigateUp()
    }
}