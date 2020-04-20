package thousand.group.healbits.views.auth.interactors

import thousand.group.healbits.global.services.storage.LocaleStorage
import thousand.group.healbits.global.system.SchedulersProvider
import thousand.group.healbits.views.auth.repositories.activity.AuthRepository

class AuthInteractor(
    private val repository: AuthRepository,
    private val schedulersProvider: SchedulersProvider,
    private val storage: LocaleStorage
) {
    fun isUserSaved(): Boolean = storage.isUserSaved()
}