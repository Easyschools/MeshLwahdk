package com.developnetwork.meshlwahdk.ui.main.dose

import androidx.lifecycle.LiveData
import com.developnetwork.meshlwahdk.BuildConfig
import com.developnetwork.meshlwahdk.base.BaseViewModel
import com.developnetwork.meshlwahdk.data.repository.DoseRepo
import com.developnetwork.meshlwahdk.data.repository.OtherRepo

abstract class BaseDoseInputViewModel( private val otherRepo: OtherRepo) : BaseViewModel() {

    fun getProducts() = callRequestLiveData { otherRepo.getProducts(BuildConfig.company_id) }

    abstract fun save(productID: Int, frequency: String, end: String):LiveData<*>

}