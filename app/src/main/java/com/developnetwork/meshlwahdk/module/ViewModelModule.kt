package com.developnetwork.meshlwahdk.module

import com.developnetwork.meshlwahdk.ui.auth.confirmnumber.ConfirmNumberViewModel
import com.developnetwork.meshlwahdk.ui.auth.forgotpassword.ForgotPasswordViewModel
import com.developnetwork.meshlwahdk.ui.auth.forgotpassword.confirmphone.ForgotPasswordConfirmationViewModel
import com.developnetwork.meshlwahdk.ui.auth.forgotpassword.resetpassword.ResetPasswordViewModel
import com.developnetwork.meshlwahdk.ui.auth.login.LoginViewModel
import com.developnetwork.meshlwahdk.ui.auth.register.CheckPhoneViewModel
import com.developnetwork.meshlwahdk.ui.auth.register.RegisterViewModel
import com.developnetwork.meshlwahdk.ui.main.changephone.ChangePhoneViewModel
import com.developnetwork.meshlwahdk.ui.main.changephone.confirm.ConfirmUpdatedPhoneViewModel
import com.developnetwork.meshlwahdk.ui.main.dose.adddose.AddDoseViewModel
import com.developnetwork.meshlwahdk.ui.main.dose.doselist.DoseListViewModel
import com.developnetwork.meshlwahdk.ui.main.dose.editdose.EditDoseViewModel
import com.developnetwork.meshlwahdk.ui.main.editprofile.EditProfileViewModel
import com.developnetwork.meshlwahdk.ui.main.history.HistoryViewModel
import com.developnetwork.meshlwahdk.ui.main.mainActivty.MainViewModel
import com.developnetwork.meshlwahdk.ui.main.notifications.NotificationViewModel
import com.developnetwork.meshlwahdk.ui.main.orderprogram.OrderProgramsViewModel
import com.developnetwork.meshlwahdk.ui.main.pharmacies.PharmaciesViewModel
import com.developnetwork.meshlwahdk.ui.main.profile.ProfileViewModel
import com.developnetwork.meshlwahdk.ui.main.program.ProgramViewModel
import com.developnetwork.meshlwahdk.ui.main.programs.ProgramsViewModel
import com.developnetwork.meshlwahdk.ui.main.reminder.ReminderViewModel
import com.developnetwork.meshlwahdk.ui.main.selectpharmacy.SelectOrderPharmacyViewModel
import com.developnetwork.meshlwahdk.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(),get()) }
    viewModel { CheckPhoneViewModel(get(), get()) }
    viewModel { ConfirmNumberViewModel(get()) }
    viewModel { RegisterViewModel(get(),get(),get(),get()) }
    viewModel { LoginViewModel(get(),get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { ForgotPasswordConfirmationViewModel(get(),get()) }
    viewModel { ResetPasswordViewModel(get(),get()) }
    viewModel { PharmaciesViewModel(get()) }
    viewModel { DoseListViewModel(get()) }
    viewModel { AddDoseViewModel(get(),get()) }
    viewModel { EditDoseViewModel(get(),get()) }
    viewModel { ProgramViewModel(get()) }
    viewModel { ProgramsViewModel(get()) }
    viewModel { OrderProgramsViewModel(get(),get()) }
    viewModel { EditProfileViewModel(get(),get()) }
    viewModel { HistoryViewModel(get(),get()) }
    viewModel { ProfileViewModel(get(),get(),get()) }
    viewModel { SelectOrderPharmacyViewModel(get()) }
    viewModel { ChangePhoneViewModel(get()) }
    viewModel { ConfirmUpdatedPhoneViewModel(get()) }
    viewModel { SplashViewModel(get(),get()) }
    viewModel { ReminderViewModel(get()) }
    viewModel { NotificationViewModel(get()) }
}