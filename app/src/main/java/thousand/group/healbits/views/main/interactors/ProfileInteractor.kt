package thousand.group.healbits.views.main.interactors

import io.reactivex.Single
import retrofit2.Response
import thousand.group.azimutgas.views.main.repositories.profile.ProfileRepository
import thousand.group.healbits.global.services.storage.LocaleStorage
import thousand.group.healbits.global.system.SchedulersProvider
import thousand.group.healbits.model.rest.RestUser
import thousand.group.healbits.model.simple.User

class ProfileInteractor(
    private val repository: ProfileRepository,
    private val schedulersProvider: SchedulersProvider,
    private val storage: LocaleStorage
) {
    fun getProfile(
        id: Int
    ): Single<Response<RestUser>> =
        repository
            .getProfile(id)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())

    fun getUser() = storage.getUserModel()

    fun setUser(model: User) = storage.saveUserModel(model)

    fun setUserAccess(save: Boolean) = storage.saveUser(save)

}