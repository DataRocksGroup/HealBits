package thousand.group.azimutgas.views.main.repositories.profile

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.model.rest.RestUser

interface ProfileRepository {
    fun getProfile(
        id: Int
    ): Single<Response<RestUser>>

}