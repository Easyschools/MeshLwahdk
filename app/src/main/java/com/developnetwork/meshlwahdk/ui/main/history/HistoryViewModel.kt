package com.developnetwork.meshlwahdk.ui.main.history

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.ProgramsRepo
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager

class HistoryViewModel(private val programsRepo: ProgramsRepo,private val sharedPreferencesManager: SharedPreferencesManager):BaseViewModel() {
    fun getRedeemedPrograms()=callRequestLiveData { programsRepo.getRedeemedPrograms(sharedPreferencesManager.userID)}
}