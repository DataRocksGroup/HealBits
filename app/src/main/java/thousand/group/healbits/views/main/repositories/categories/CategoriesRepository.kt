package thousand.group.healbits.views.main.repositories.categories

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.model.rest.RestCategory

interface CategoriesRepository {
    fun getCategories(): Single<Response<RestCategory>>
}