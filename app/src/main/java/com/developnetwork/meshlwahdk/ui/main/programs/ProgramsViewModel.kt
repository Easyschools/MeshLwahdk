package com.developnetwork.meshlwahdk.ui.main.programs

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.ProgramsRepo

class ProgramsViewModel(private val programsRepo: ProgramsRepo):BaseViewModel() {
    fun getPrograms()=callRequestLiveData { programsRepo.getPrograms() }
}