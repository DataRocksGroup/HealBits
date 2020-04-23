package thousand.group.healbits.views.main.repositories.exercise

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.global.services.api.ServerService
import thousand.group.healbits.model.rest.RestExercise

class ExerciseRepositoryImpl(
    private val service: ServerService
) : ExerciseRepository {

    override fun getExercises(id: Int): Single<Response<RestExercise>> =
        service.getExercises(id.toString())
}