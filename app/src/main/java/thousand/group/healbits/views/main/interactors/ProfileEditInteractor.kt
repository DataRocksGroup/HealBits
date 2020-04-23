package thousand.group.healbits.views.main.interactors

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.global.services.storage.LocaleStorage
import thousand.group.healbits.global.system.SchedulersProvider
import thousand.group.healbits.model.simple.User
import thousand.group.healbits.views.main.repositories.profile_edit.ProfileEditRepository

class ProfileEditInteractor(
    private val repository: ProfileEditRepository,
    private val schedulersProvider: SchedulersProvider,
    private val storage: LocaleStorage
) {
    fun edit(id: Int, params: MutableMap<String, String>): Single<Response<User>> =
        repository.edit(id, params)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())

    fun saveUser(user: User) = storage.saveUserModel(user)

    fun saveUserAccess(access: Boolean) = storage.saveUser(access)
}