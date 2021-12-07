package com.developnetwork.meshlwahdk.utils.managers

import android.content.Context
import android.content.SharedPreferences
import com.developnetwork.meshlwahdk.data.model.User

interface SharedPreferencesManager {
    var userToken: String

    var userID: Int

    var notificationToken: String

    var userName: String

    var userEmail: String

    var userPhone: String

    var userImage: String

    var isLoggedIn: Boolean

    var choseLanguage: Boolean

    var termsAgreed: Boolean

    var userData: User

    var firstTime: Boolean

    var selectedProgram: Int

    var companyPhone: String

    fun saveUserData(user: User, saveID: Boolean = true)
    fun clearData()

}

class SharedPreferencesManagerImpl(context: Context) : SharedPreferencesManager {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            sharedPreferencesKey,
            Context.MODE_PRIVATE
        )
    private val editor = sharedPreferences.edit()


    override
    var userToken: String
        get() = getString(userTokenKey)
        set(token) {
            editor.putString(userTokenKey, "Bearer $token").apply()
        }

    override var userID: Int
        get() = getInt(userIDKey)
        set(id) {
            editor.putInt(userIDKey, id).apply()
        }

    override var notificationToken: String
        get() = getString(notificationTokenKey)
        set(token) {
            editor.putString(notificationTokenKey, token).apply()
        }
    override var userName: String
        get() = getString(userNameKey)
        set(token) {
            editor.putString(userNameKey, token).apply()
        }

    override var userEmail: String
        get() = getString(userEmailKey)
        set(token) {
            editor.putString(userEmailKey, token).apply()
        }
    override var userPhone: String
        get() = getString(userPhoneKey)
        set(value) {
            editor.putString(userPhoneKey, value).apply()

        }
    override var userImage: String
        get() = getString(userImageKey)
        set(token) {
            editor.putString(userImageKey, token).apply()
        }

    override var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(isLoggedInKey, false)
        set(b) {
            editor.putBoolean(isLoggedInKey, b).apply()
        }

    override var choseLanguage: Boolean
        get() = sharedPreferences.getBoolean(choseLanguageKey, false)
        set(b) {
            editor.putBoolean(choseLanguageKey, b).apply()
        }

    override var termsAgreed: Boolean
        get() = sharedPreferences.getBoolean(termsAgreedKey, false)
        set(b) {
            editor.putBoolean(termsAgreedKey, b).apply()
        }

    override var firstTime: Boolean
        get() = sharedPreferences.getBoolean(FirstTimeKey, false)
        set(b) {
            editor.putBoolean(FirstTimeKey, b).apply()
        }

    override var selectedProgram: Int
        get() = getInt(selectedProgramIDKEY)
        set(id) {
            editor.putInt(selectedProgramIDKEY, id).apply()
        }

    override var companyPhone: String
        get() = getString(companyIDKEY)
        set(value) {
            editor.putString(companyIDKEY, value).apply()
        }

    override var userData: User
        get() {
            return User().create(getString(userDataKey))
        }
        set(user: User) {
            editor.putString(userDataKey, user.serialize()).apply()
        }


    override fun clearData() {
        editor.clear().apply()
    }

    private fun getString(key: String): String {
        sharedPreferences.getString(key, "").let { s ->
            return if (s.isNullOrBlank())
                ""
            else
                s
        }
    }

    private fun getInt(key: String): Int {
        sharedPreferences.getInt(key, 0).let { s ->
            return if (s == null)
                0
            else
                s
        }
    }

    override fun saveUserData(user: User, saveID: Boolean) {
        if (!user.token.isNullOrBlank())
            userToken = user.token
        if (!user.email.isNullOrBlank())
            userEmail = user.email!!
        if (!user.phone.isNullOrBlank())
            userPhone = user.phone!!
        if (!user.name.isNullOrBlank())
            userName = user.name!!
        if (!user.img.isNullOrBlank())
            userImage = user.img
//        if (!user.slug.isNullOrBlank())
//            slug = user.slug


        if (saveID)
            userID = user.id
        userData = user
        isLoggedIn = true
    }

    companion object {
        private const val sharedPreferencesKey = "USERDATA"

        private const val userTokenKey = "API_TOKEN"
        private const val userIDKey = "USERID"
        private const val userNameKey = "USERNAME"
        private const val userPhoneKey = "USERPHONE"
        private const val userEmailKey = "USEREMAIL"
        private const val userImageKey = "USERIMAGE"
        private const val userDataKey = "USERDATA"
        private const val userSlugKey = "USERSlug"
        private const val choseLanguageKey = "CHOSELANGUAGE"
        private const val termsAgreedKey = "TERMSAGREED"
        private const val FirstTimeKey = "TERMSAGREED"
        private const val notificationTokenKey = "NotificationToken"

        private const val selectedProgramIDKEY = "SelectedProgramID"
        private const val companyIDKEY = "CompanyID"


        private const val isLoggedInKey = "LOGIN"
    }
}
