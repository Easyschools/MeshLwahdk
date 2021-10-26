package com.developnetwork.meshlwahdk.module

import com.developnetwork.meshlwahdk.ui.auth.confirmnumber.ConfirmNumberViewModel
import com.developnetwork.meshlwahdk.ui.auth.forgotpassword.ForgotPasswordViewModel
import com.developnetwork.meshlwahdk.ui.auth.forgotpassword.confirmphone.ForgotPasswordConfirmationViewModel
import com.developnetwork.meshlwahdk.ui.auth.forgotpassword.resetpassword.ResetPasswordViewModel
import com.developnetwork.meshlwahdk.ui.auth.login.LoginViewModel
import com.developnetwork.meshlwahdk.ui.auth.register.CheckPhoneViewModel
import com.developnetwork.meshlwahdk.ui.auth.register.RegisterViewModel
import com.developnetwork.meshlwahdk.ui.main.dose.BaseDoseInputViewModel
import com.developnetwork.meshlwahdk.ui.main.dose.adddose.AddDoseViewModel
import com.developnetwork.meshlwahdk.ui.main.dose.doselist.DoseListViewModel
import com.developnetwork.meshlwahdk.ui.main.dose.editdose.EditDoseViewModel
import com.developnetwork.meshlwahdk.ui.main.pharmacies.PharmaciesViewModel
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
    viewModel { PharmaciesViewModel(get()) }
    viewModel { DoseListViewModel(get()) }
    viewModel { AddDoseViewModel(get(),get()) }
    viewModel { EditDoseViewModel(get(),get()) }
}