package thousand.group.healbits.views.auth.repositories.login

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.global.services.api.ServerService
import thousand.group.healbits.model.rest.RestUser

class LoginRepositoryImpl(
    private val service: ServerService
) : LoginRepository {

    override fun signIn(phone: String, password: String): Single<Response<RestUser>> =
        service.signIn(phone, password)
}