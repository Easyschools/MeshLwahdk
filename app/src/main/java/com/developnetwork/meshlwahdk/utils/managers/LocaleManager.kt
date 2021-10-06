package com.developnetwork.meshlwahdk.utils.managers


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Build.VERSION_CODES.N
import androidx.preference.PreferenceManager
import com.developnetwork.meshlwahdk.utils.managers.LocaleManager.Companion.LANGUAGE_ENGLISH
import com.developnetwork.meshlwahdk.utils.managers.LocaleManager.Companion.updateResources
import java.util.*

interface LocaleManager {
    val language: String
    val isEnglish: Boolean
    fun setLocale(c: Context): Context
    fun setNewLocale(c: Context, language: String): Context

    companion object {
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_Arabic = "ar"


        fun getLocale(res: Resources): Locale {
            val config = res.configuration
            return if (Build.VERSION.SDK_INT >= N) {
                config.locales.get(0)
            } else config.locale
        }

        fun updateResources(context: Context, language: String): Context {
            var context = context
            val locale = Locale(language)
            Locale.setDefault(locale)

            val res = context.resources
            val config = Configuration(res.configuration)
            if (Build.VERSION.SDK_INT >= 21) {
                config.setLocale(locale)
                context = context.createConfigurationContext(config)
            } else {
                config.locale = locale
                res.updateConfiguration(config, res.displayMetrics)
            }

            return context
        }

        fun getLocale(lang: String): Locale {
            return Locale(lang)
        }
    }
}

class LocaleManagerImpl(context: Context) : LocaleManager {

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val LANGUAGE_KEY = "language_key"

    override val language: String
        get() {
            val prf = prefs.getString(LANGUAGE_KEY, null)
            return if (prf == null) {

                val locale: Locale = if (Build.VERSION.SDK_INT >= N)
                    Resources.getSystem().configuration.locales.get(0)
                else
                    Resources.getSystem().configuration.locale

                locale.language.toLowerCase(Locale.ROOT)
            } else {
                prf
            }
        }

    override val isEnglish: Boolean
        get() {
            return LANGUAGE_ENGLISH == language
        }

    override fun setLocale(c: Context): Context {
        return updateResources(c, language)
    }

    override fun setNewLocale(c: Context, language: String): Context {
        persistLanguage(language)
        return updateResources(c, language)
    }

    @SuppressLint("ApplySharedPref")
    private fun persistLanguage(language: String) {
        prefs.edit().putString(LANGUAGE_KEY, language).commit()
    }
}