package thousand.group.healbits.views.main.repositories.profile_edit

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.model.simple.User

interface ProfileEditRepository {
    fun edit(id: Int, params: MutableMap<String, String>): Single<Response<User>>

}