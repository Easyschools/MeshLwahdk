package com.developnetwork.meshlwahdk.module

import com.developnetwork.meshlwahdk.data.repository.*
import org.koin.dsl.module

val repoModule = module {
    factory<AuthRepo> { AuthRepoImpl(get()) }
    factory<OtherRepo> { OthersRepoImpl(get()) }
    factory<DoseRepo> { DoseRepoImpl(get()) }
    factory<ProgramsRepo> { ProgramsRepoImpl(get()) }
    factory<UserRepo> { UserRepoImpl(get(),get()) }
    factory<NotificationsRepo> { NotificationsRepoImpl(get()) }
}