package com.developnetwork.meshlwahdk.utils
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber


class ExceptionReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        val throwable: Throwable =
            t ?: Exception(message)


        // Crashlytics
        val crashlytics = FirebaseCrashlytics.getInstance()
        tag?.let {
            crashlytics.setCustomKey("TAG", tag)
        }
        crashlytics.log(message)
        crashlytics.recordException(throwable)
    }
}