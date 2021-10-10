package com.developnetwork.meshlwahdk.utils

import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.app.MeshLwahdkApplication
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManagerImpl
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException


private fun responseErrorHandler(response: String, responsecode: Int): String {
    val context = MeshLwahdkApplication.appContext.applicationContext
    return when {
        responsecode < 500 -> {
//            if (responsecode == 401) {
//                SharedPreferencesManagerImpl(LeedoApplication.appContext).isLoggedIn = false
//
//                val i = Intent(context, AuthActivity::class.java)
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//                context.startActivity(i)
//            }
            try {
                val responseObject = JSONObject(response)
//                if (responseObject.has("data")) {
//                    return responseObject.getString("data")
//                }
//                if (responseObject.has("errors")) {
//                    val dataObject = responseObject.getJSONObject("errors")
//                    if (dataObject.has("email"))
//                        return dataObject.getJSONArray("email").getString(0)
//                }
                if (responseObject.has("errors")) {
                    val error = responseObject.get("errors")
                    if (error is String)
                        return error
                    if (error is JSONObject) {
                        for (key: String in error.keys().iterator()) {
                            return error.getString(key)
                        }
                    }
                }
                responseObject.getString("message")
            } catch (e: Exception) {
                Timber.e(e)
                if (null != e.message)
                    e.message!!
                else
                    context.getString(R.string.error)

            }
        }
        responsecode == 500 -> {
            context.getString(R.string.server_error)
        }
        else -> {
            context.getString(R.string.error)
        }
    }
}

private fun failureHandler(t: Throwable): String {
    val context = MeshLwahdkApplication.appContext.applicationContext
    return if (t is IOException) {
        context.getString(R.string.no_internet)
    } else {
        Timber.d(t)
        context.getString(R.string.error)
    }
}

fun errorHandler(throwable: Throwable): String {

    val context = MeshLwahdkApplication.appContext.applicationContext
    try {
        return if (throwable is HttpException)
            responseErrorHandler(throwable.response()!!.errorBody()!!.string(), throwable.code())
        else failureHandler(throwable)
    } catch (e: Exception) {
        Timber.d(e)
    }
    return context.getString(R.string.error)

}