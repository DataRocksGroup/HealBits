package thousand.group.healbits.views.auth.interactors

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.global.services.storage.LocaleStorage
import thousand.group.healbits.global.system.SchedulersProvider
import thousand.group.healbits.model.simple.User
import thousand.group.healbits.views.auth.repositories.signup.SignUpRepository

class SignUpInteractor(
    private val repository: SignUpRepository,
    private val schedulersProvider: SchedulersProvider,
    private val storage: LocaleStorage
) {
    fun signUp(params: MutableMap<String, String>): Single<Response<User>> =
        repository.signUp(params)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())

    fun saveUser(user: User) = storage.saveUserModel(user)

    fun saveUserAccess(access: Boolean) = storage.saveUser(access)

}