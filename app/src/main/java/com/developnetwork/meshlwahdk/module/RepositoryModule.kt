package com.developnetwork.meshlwahdk.module

import com.developnetwork.meshlwahdk.data.repository.AuthRepo
import com.developnetwork.meshlwahdk.data.repository.AuthRepoImpl
import com.developnetwork.meshlwahdk.data.repository.OtherRepo
import com.developnetwork.meshlwahdk.data.repository.OthersRepoImpl
import org.koin.dsl.module

val repoModule = module {
    factory<AuthRepo> { AuthRepoImpl(get()) }
    factory<OtherRepo> { OthersRepoImpl(get()) }
}