package thousand.group.healbits.views.main.repositories.exercise

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.model.rest.RestExercise

interface ExerciseRepository {
    fun getExercises(id: Int): Single<Response<RestExercise>>
}