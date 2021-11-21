package com.developnetwork.meshlwahdk.utils.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

inline fun <reified T> koinInject(): T {
    return object : KoinComponent {
        val value: T by inject()
    }.value
}


fun callUS(context: Context) {
    val sharedPreferencesManager = koinInject<SharedPreferencesManager>()

    if (!sharedPreferencesManager.companyPhone.isNullOrBlank()) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + sharedPreferencesManager.companyPhone)
        startActivity(context, dialIntent, null)
    }
}