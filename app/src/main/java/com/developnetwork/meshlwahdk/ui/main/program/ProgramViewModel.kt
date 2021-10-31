package com.developnetwork.meshlwahdk.ui.main.program

import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.ProgramsRepo

class ProgramViewModel(private val programsRepo: ProgramsRepo) : BaseViewModel() {
    fun getProgram(id: Int) = callRequestLiveData { programsRepo.getProgram(id) }

}