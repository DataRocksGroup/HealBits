package thousand.group.healbits.global.services.storage

import com.pixplicity.easyprefs.library.Prefs

object LocaleStorage {

    const val PREF_NO_VAL = "PREF_NO_VAL"

    private const val PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN"
    private const val PREF_SAVE_USER = "PREF_SAVE_USER"
    private const val PREF_LANG_DEFAULT_VAL = "ru"
    private const val PREF_LANG_CODE = "PREF_LANG_CODE"
    private const val PREF_PERM_AUDIO = "PREF_PERM_AUDIO"
    private const val PREF_PERM_PUSH = "PREF_PERM_PUSH"
    private const val PREF_SAVED_USER_MODEL = "PREF_SAVED_USER_MODEL"
    private const val PREF_FIRST_LAUNCHED = "PREF_FIRST_LAUNCHED"
    private const val PREF_PUSH_RECEIVED = "PREF_PUSH_RECEIVED"

    fun setAccessToken(accessToken: String) = Prefs.putString(PREF_ACCESS_TOKEN, accessToken)

    fun getAccessToken(): String = Prefs.getString(PREF_ACCESS_TOKEN, PREF_NO_VAL)

    fun saveUser(saveUser: Boolean) = Prefs.putBoolean(PREF_SAVE_USER, saveUser)

    fun isUserSaved(): Boolean = Prefs.getBoolean(PREF_SAVE_USER, false)

    fun setLanguage(lang: String) = Prefs.putString(PREF_LANG_CODE, lang)

    fun getLanguage(): String = Prefs.getString(PREF_LANG_CODE, PREF_LANG_DEFAULT_VAL)

    fun setSound(sound: Int) = Prefs.putInt(PREF_PERM_AUDIO, sound)

    fun getSound(): Int = Prefs.getInt(PREF_PERM_AUDIO, 1)

    fun setPush(push: Int) = Prefs.putInt(PREF_PERM_PUSH, push)

    fun getPush(): Int = Prefs.getInt(PREF_PERM_PUSH, 1)

//    fun saveUserModel(user: User) =
//        Prefs.putString(PREF_SAVED_USER_MODEL, GsonHelper.getJsonFromObject(user))
//
//    fun deleteUserModel() = Prefs.remove(PREF_SAVED_USER_MODEL)
//
//    fun getUserModel(): User? {
//        val modelJson = Prefs.getString(PREF_SAVED_USER_MODEL, null)
//
//        modelJson?.apply {
//            return GsonHelper.getObjectFromString(this, User::class.java)
//        }
//
//        return null
//    }

    fun setFirstTimeLaunched(firstTimeLaunched: Boolean) =
        Prefs.putBoolean(PREF_FIRST_LAUNCHED, firstTimeLaunched)

    fun isFirstTimeLaunched(): Boolean = Prefs.getBoolean(PREF_FIRST_LAUNCHED, true)

    fun setPushReceived(received: Boolean) = Prefs.putBoolean(PREF_PUSH_RECEIVED, received)

    fun isPushReceived(): Boolean = Prefs.getBoolean(PREF_PUSH_RECEIVED, false)


}