package thousand.group.azimutgas.views.main.repositories.profile

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.global.services.api.ServerService
import thousand.group.healbits.model.rest.RestUser

class ProfileRepositoryImpl(
    private val service: ServerService
) : ProfileRepository {

    override fun getProfile(id: Int): Single<Response<RestUser>> = service.getProfile(id.toString())
}