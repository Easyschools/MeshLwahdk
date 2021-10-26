package com.developnetwork.meshlwahdk.module

import com.developnetwork.meshlwahdk.data.repository.*
import org.koin.dsl.module

val repoModule = module {
    factory<AuthRepo> { AuthRepoImpl(get()) }
    factory<OtherRepo> { OthersRepoImpl(get()) }
    factory<DoseRepo> { DoseRepoImpl(get()) }
}