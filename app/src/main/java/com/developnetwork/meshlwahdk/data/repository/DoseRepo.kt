package com.developnetwork.meshlwahdk.data.repository

import com.developnetwork.meshlwahdk.data.model.Dose
import com.developnetwork.meshlwahdk.data.network.Service

interface DoseRepo {
    suspend fun addDose(
        productID: Int,
        frequency: String,
        duration: String
    ): Dose

    suspend fun editDose(
        id: Int,
        productID: Int,
        frequency: String,
        duration: String
    ): Dose

    suspend fun getDoses(): List<Dose>
    suspend fun getDose(id: Int): Dose

}

class DoseRepoImpl(private val service: Service) : DoseRepo {
    override suspend fun addDose(productID: Int, frequency: String, duration: String): Dose {
        return service.addDose(productID, frequency, duration).data
    }

    override suspend fun editDose(
        id: Int,
        productID: Int,
        frequency: String,
        duration: String
    ): Dose {
        return service.editDose(id, productID, frequency, duration).data
    }

    override suspend fun getDoses(): List<Dose> {
        return service.getDoses().data
    }

    override suspend fun getDose(id: Int): Dose {
        return service.getDose(id).data
    }
}