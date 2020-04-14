package thousand.group.healbits.global.helpers

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import thousand.group.healbits.global.services.storage.LocaleStorage
import java.util.*

class LocaleHelper(base: Context) : ContextWrapper(base) {
    companion object {

        @SuppressLint("ObsoleteSdkInt")
        fun wrap(context: Context): ContextWrapper {
            var contextClone = context
            try {
                val config = contextClone.resources.configuration

                val locale = Locale(LocaleStorage.getLanguage())

                Log.i("LocaleHelper", LocaleStorage.getLanguage())

                Locale.setDefault(locale)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    setSystemLocale(config, locale)
                } else {
                    setSystemLocaleLegacy(config, locale)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    config.setLayoutDirection(locale)
                    contextClone = contextClone.createConfigurationContext(config)
                } else {
                    contextClone.resources.updateConfiguration(
                        config,
                        contextClone.resources.displayMetrics
                    )
                }
            } catch (e: ClassCastException) {
                e.printStackTrace()
            }

            return LocaleHelper(contextClone)
        }

        fun getSystemLocaleLegacy(config: Configuration): Locale {
            return config.locale
        }

        @TargetApi(Build.VERSION_CODES.N)
        fun getSystemLocale(config: Configuration): Locale {
            return config.locales.get(0)
        }

        fun setSystemLocaleLegacy(config: Configuration, locale: Locale) {
            config.locale = locale
        }

        @TargetApi(Build.VERSION_CODES.N)
        fun setSystemLocale(config: Configuration, locale: Locale) {
            config.setLocale(locale)
        }
    }
}