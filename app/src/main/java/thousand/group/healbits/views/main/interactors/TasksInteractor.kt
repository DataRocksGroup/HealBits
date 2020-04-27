package thousand.group.healbits.views.main.interactors

import thousand.group.healbits.global.services.storage.LocaleStorage
import thousand.group.healbits.global.system.SchedulersProvider
import thousand.group.healbits.model.simple.User
import thousand.group.healbits.views.main.repositories.tasks.TasksRepository

class TasksInteractor(
    private val repository: TasksRepository,
    private val schedulersProvider: SchedulersProvider,
    private val storage: LocaleStorage
) {

    fun getTasks(user_id: Int, c_date: String) = repository
        .getTasks(user_id, c_date)
        .subscribeOn(schedulersProvider.io())
        .observeOn(schedulersProvider.ui())

    fun getUserModel(): User? = storage.getUserModel()

    fun addTasks(params: MutableMap<String, String>) =
        repository
            .addTasks(params)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())

    fun changeTaskStatus(
        id: Int,
        status: Int
    ) = repository
        .changeTaskStatus(id, status)
        .subscribeOn(schedulersProvider.io())
        .observeOn(schedulersProvider.ui())
}