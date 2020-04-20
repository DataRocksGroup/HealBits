package thousand.group.healbits.views.auth.interactors

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.global.services.storage.LocaleStorage
import thousand.group.healbits.global.system.SchedulersProvider
import thousand.group.healbits.model.rest.RestUser
import thousand.group.healbits.model.simple.User
import thousand.group.healbits.views.auth.repositories.login.LoginRepository

class LoginInteractor(
    private val repository: LoginRepository,
    private val schedulersProvider: SchedulersProvider,
    private val storage: LocaleStorage
) {

//    fun signIn(params: MutableMap<String, RequestBody>): Single<Response<User>> = repository
//        .signIn(params)
//        .subscribeOn(schedulersProvider.io())
//        .observeOn(schedulersProvider.ui())

    fun saveUser(user: User) = storage.saveUserModel(user)

    fun saveUserAccess(access: Boolean) = storage.saveUser(access)

    fun saveAccessToken(token: String) = storage.setAccessToken(token)

    fun signIn(
        phone: String,
        password: String
    ): Single<Response<RestUser>> =
        repository
            .signIn(phone, password)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
}