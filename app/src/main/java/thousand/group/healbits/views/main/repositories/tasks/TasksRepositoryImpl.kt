package thousand.group.healbits.views.main.repositories.tasks

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Response
import thousand.group.healbits.global.services.api.ServerService
import thousand.group.healbits.model.rest.RestTask
import thousand.group.healbits.model.simple.Task

class TasksRepositoryImpl(
    private val service: ServerService
) : TasksRepository {

    override fun getTasks(user_id: Int, c_date: String): Single<Response<RestTask>> =
        service.getTasks(user_id.toString(), c_date)

    override fun addTasks(params: MutableMap<String, String>): Single<Response<Task>> =
        service.addTasks(params)

    override fun changeTaskStatus(id: Int, status: Int): Completable =
        service.changeTaskStatus(id.toString(), status.toString())

    override fun deleteTask(id: Int): Completable = service.deleteTask(id.toString())
}