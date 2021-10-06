package com.developnetwork.meshlwahdk.module

import com.developnetwork.meshlwahdk.BuildConfig
import com.developnetwork.meshlwahdk.data.network.Service
import com.developnetwork.meshlwahdk.utils.extensions.koinInject
import com.developnetwork.meshlwahdk.utils.managers.LocaleManager
import com.developnetwork.meshlwahdk.utils.managers.LocaleManagerImpl
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManager
import com.developnetwork.meshlwahdk.utils.managers.SharedPreferencesManagerImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModules = module {
    // The Retrofit service using our custom HTTP client instance as a singleton
    single {
        createWebService<Service>(
            okHttpClient = createHttpClient(),
            baseUrl = BuildConfig.SERVER_URL
        )
    }

    factory<SharedPreferencesManager> {
        SharedPreferencesManagerImpl(get())
    }

    factory<LocaleManager> {
        LocaleManagerImpl(get())
    }
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
//    val loggingInterceptor = HttpLoggingInterceptor()
//    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(headersInterceptor)
        .readTimeout(50, TimeUnit.SECONDS)
        .writeTimeout(50, TimeUnit.SECONDS)
        .connectTimeout(50, TimeUnit.SECONDS)
//        .addNetworkInterceptor(StethoInterceptor())
//        .addInterceptor(loggingInterceptor)
        .build()
}

private val headersInterceptor: Interceptor = Interceptor { chain ->
    val sharedPreferencesManager = koinInject<SharedPreferencesManager>()
    val localeManager = koinInject<LocaleManager>()

    val token = sharedPreferencesManager.userToken

    var request = chain.request()
    request = request.newBuilder()
        .header("Authorization", token)
        .addHeader("Accept", "application/json")
        .addHeader("X-localization", localeManager.language)
        .build()
    chain.proceed(request)
}