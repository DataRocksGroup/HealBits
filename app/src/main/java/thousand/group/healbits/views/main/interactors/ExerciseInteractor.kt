package thousand.group.healbits.views.main.interactors

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.global.services.storage.LocaleStorage
import thousand.group.healbits.global.system.SchedulersProvider
import thousand.group.healbits.model.rest.RestExercise
import thousand.group.healbits.views.main.repositories.exercise.ExerciseRepository

class ExerciseInteractor(
    private val repository: ExerciseRepository,
    private val schedulersProvider: SchedulersProvider,
    private val storage: LocaleStorage
) {
    fun getExercises(id: Int): Single<Response<RestExercise>> = repository
        .getExercises(id)
        .subscribeOn(schedulersProvider.io())
        .observeOn(schedulersProvider.ui())
}