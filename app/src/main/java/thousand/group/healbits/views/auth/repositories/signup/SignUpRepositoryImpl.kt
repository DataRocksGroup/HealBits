package thousand.group.healbits.views.auth.repositories.signup

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.global.services.api.ServerService
import thousand.group.healbits.model.simple.User


class SignUpRepositoryImpl(
    private val service: ServerService
) : SignUpRepository {

    override fun signUp(params: MutableMap<String, String>): Single<Response<User>> =
        service.signUp(params)

}