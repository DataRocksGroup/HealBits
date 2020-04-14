package thousand.group.healbits.app

import android.app.Application
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import thousand.group.healbits.global.extentions.appModule

class HealBitsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HealBitsApp)
            modules(appModule)
        }

        RxJavaPlugins.setErrorHandler { }

        initPrefs()

    }

    private fun initPrefs() {
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }
}