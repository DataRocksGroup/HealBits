package thousand.group.healbits.global.extentions

import thousand.group.healbits.global.di.networkModule
import thousand.group.healbits.global.di.utilModule
import thousand.group.healbits.views.auth.di.interactorAuthModule
import thousand.group.healbits.views.auth.di.presenterAuthModule
import thousand.group.healbits.views.main.di.interactorMainModule
import thousand.group.healbits.views.main.di.presenterMainModule

val appModule = listOf(
    networkModule,
    utilModule,
    interactorAuthModule,
    presenterAuthModule,
    interactorMainModule,
    presenterMainModule
)