package com.developnetwork.meshlwahdk.ui.main.history

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.ProgramsRepo

class HistoryViewModel(private val programsRepo: ProgramsRepo):BaseViewModel() {
    fun getRedeemedPrograms()=callRequestLiveData { programsRepo.getRedeemedPrograms()}
}