package thousand.group.healbits.views.main.repositories.profile_edit

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.global.services.api.ServerService
import thousand.group.healbits.model.simple.User

class ProfileEditRepositoryImpl(
    private val service: ServerService
) : ProfileEditRepository {

    override fun edit(id: Int, params: MutableMap<String, String>): Single<Response<User>> =
        service.edit(id.toString(), params)
}