package thousand.group.healbits.global.di

import android.content.Context
import org.koin.dsl.module
import thousand.group.healbits.global.helpers.MessageStatusHelper
import thousand.group.healbits.global.helpers.ProgressBarHelper
import thousand.group.healbits.global.services.storage.LocaleStorage
import thousand.group.healbits.global.system.AppSchedulers
import thousand.group.healbits.global.system.ResourceManager
import thousand.group.healbits.global.system.SchedulersProvider

val utilModule = module {
    single<SchedulersProvider> { AppSchedulers() }
    single { MessageStatusHelper() }
    single { ProgressBarHelper() }
    single { LocaleStorage }

    factory { (context: Context) ->
        ResourceManager(context)
    }
}