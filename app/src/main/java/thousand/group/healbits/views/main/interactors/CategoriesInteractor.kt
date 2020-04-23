package thousand.group.healbits.views.main.interactors

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.global.services.storage.LocaleStorage
import thousand.group.healbits.global.system.SchedulersProvider
import thousand.group.healbits.model.rest.RestCategory
import thousand.group.healbits.views.main.repositories.categories.CategoriesRepository

class CategoriesInteractor(
    private val repository: CategoriesRepository,
    private val schedulersProvider: SchedulersProvider,
    private val storage: LocaleStorage
) {
    fun getCategories(): Single<Response<RestCategory>> =
        repository
            .getCategories()
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
}