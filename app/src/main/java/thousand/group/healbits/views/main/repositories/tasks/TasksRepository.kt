package thousand.group.healbits.views.main.repositories.tasks

import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.model.rest.RestTask
import thousand.group.healbits.model.simple.Task

interface TasksRepository {

    fun getTasks(
        user_id: Int,
        c_date: String
    ): Single<Response<RestTask>>

    fun addTasks(params: MutableMap<String, String>): Single<Response<Task>>
}