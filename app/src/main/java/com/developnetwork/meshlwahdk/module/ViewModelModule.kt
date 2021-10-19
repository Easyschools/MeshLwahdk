package com.developnetwork.meshlwahdk.module

import com.developnetwork.meshlwahdk.ui.auth.confirmnumber.ConfirmNumberViewModel
import com.developnetwork.meshlwahdk.ui.auth.forgotpassword.ForgotPasswordViewModel
import com.developnetwork.meshlwahdk.ui.auth.forgotpassword.confirmphone.ForgotPasswordConfirmationViewModel
import com.developnetwork.meshlwahdk.ui.auth.forgotpassword.resetpassword.ResetPasswordViewModel
import com.developnetwork.meshlwahdk.ui.auth.login.LoginViewModel
import com.developnetwork.meshlwahdk.ui.auth.register.CheckPhoneViewModel
import com.developnetwork.meshlwahdk.ui.auth.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CheckPhoneViewModel(get(), get()) }
    viewModel { ConfirmNumberViewModel(get()) }
    viewModel { RegisterViewModel(get(),get(),get()) }
    viewModel { LoginViewModel(get(),get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { ForgotPasswordConfirmationViewModel(get()) }
    viewModel { ResetPasswordViewModel(get()) }
}