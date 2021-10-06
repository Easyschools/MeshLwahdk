package com.developnetwork.meshlwahdk.utils.extensions

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

inline fun <reified T> koinInject(): T {
    return object : KoinComponent {
        val value: T by inject()
    }.value
}
