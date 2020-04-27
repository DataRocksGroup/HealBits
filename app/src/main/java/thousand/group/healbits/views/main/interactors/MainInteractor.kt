package thousand.group.healbits.views.main.interactors

import thousand.group.healbits.global.services.storage.LocaleStorage
import thousand.group.healbits.global.system.SchedulersProvider
import thousand.group.healbits.views.main.repositories.activity.MainRepository

class MainInteractor(
    private val repository: MainRepository,
    private val schedulersProvider: SchedulersProvider,
    private val storage: LocaleStorage
) {
    fun deleteUser() = storage.deleteUserModel()

    fun setAccessToken(token: String) = storage.setAccessToken(token)

    fun saveUser(save: Boolean) = storage.saveUser(save)
}