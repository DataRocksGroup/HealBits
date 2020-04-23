package thousand.group.healbits.views.main.repositories.categories

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.global.services.api.ServerService
import thousand.group.healbits.model.rest.RestCategory

class CategoriesRepositoryImpl(
    private val service: ServerService
) : CategoriesRepository {

    override fun getCategories(): Single<Response<RestCategory>> = service.getCategories()
}