package com.developnetwork.meshlwahdk.ui.splash

import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {
    private val viewModel: SplashViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun init() {
        viewModel.getCompany()
    }

}