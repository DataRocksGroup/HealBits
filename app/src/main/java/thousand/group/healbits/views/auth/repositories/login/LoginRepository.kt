package thousand.group.healbits.views.auth.repositories.login

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.model.rest.RestUser

interface LoginRepository {
    fun signIn(
        phone: String,
        password: String
    ): Single<Response<RestUser>>
}