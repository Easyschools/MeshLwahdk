package com.developnetwork.meshlwahdk.app

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.developnetwork.meshlwahdk.BuildConfig
import com.developnetwork.meshlwahdk.module.appModules
import com.developnetwork.meshlwahdk.module.repoModule
import com.developnetwork.meshlwahdk.module.viewModelModule
import com.developnetwork.meshlwahdk.utils.ExceptionReportingTree
import com.developnetwork.meshlwahdk.utils.managers.LocaleManager
import com.developnetwork.meshlwahdk.utils.managers.LocaleManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class MeshLwahdkApplication: Application() {
    private lateinit var localeManager: LocaleManager

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
//        FirebaseApp.initializeApp(appContext)
//        Stetho.initializeWithDefaults(this)

        startKoin {
            androidContext(this@MeshLwahdkApplication)
            modules(listOf(appModules, repoModule, viewModelModule))
        }

        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else ExceptionReportingTree())
    }

    override fun attachBaseContext(base: Context) {
        localeManager = LocaleManagerImpl(base)
        super.attachBaseContext(localeManager.setLocale(base))

//        SplitCompat.install(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeManager.setLocale(this)
    }
}
