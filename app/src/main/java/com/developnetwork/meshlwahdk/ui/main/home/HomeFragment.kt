package com.developnetwork.meshlwahdk.ui.main.home

import android.os.Bundle
import android.view.View
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseFragment
import com.developnetwork.meshlwahdk.utils.extensions.setImageURL
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment() {
    private val sharedPreferencesManager: SharedPreferencesManager by inject()

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleUserData()
    }


    private fun handleUserData() {
        val user = sharedPreferencesManager.userData
        if (!user.img.isNullOrBlank())
            profileImage.setImageURL(user.img)

        name.text = user.name
    }
}