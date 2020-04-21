package thousand.group.healbits.views.auth.repositories.signup

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.model.simple.User

interface SignUpRepository {

    fun signUp(params: MutableMap<String, String>): Single<Response<User>>

}