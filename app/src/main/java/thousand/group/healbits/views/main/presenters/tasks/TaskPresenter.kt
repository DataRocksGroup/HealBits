package thousand.group.healbits.views.main.presenters.tasks

import android.content.Context
import android.text.Editable
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.prolificinteractive.materialcalendarview.CalendarDay
import thousand.group.healbits.R
import thousand.group.healbits.global.constants.requests.TaskRequest
import thousand.group.healbits.global.constants.simple.AppConstants
import thousand.group.healbits.global.helpers.ResErrorHelper
import thousand.group.healbits.global.presentation.BasePresenter
import thousand.group.healbits.global.system.ResourceManager
import thousand.group.healbits.model.simple.Task
import thousand.group.healbits.model.simple.User
import thousand.group.healbits.views.main.interactors.TasksInteractor

@InjectViewState
class TaskPresenter(
    override val context: Context,
    private val resourceManager: ResourceManager,
    private val interactor: TasksInteractor
) : BasePresenter<TaskView>() {

    companion object {
        internal const val TAG = "TaskPresenter"
    }

    private var user: User? = null
    private var dateStr = ""

    private val dimen = resourceManager.getDimension(R.dimen.margin_between_items).toInt()
    private val params = mutableMapOf<String, String>()


    override fun internetSuccess() {
    }

    override fun internetError() {
    }

    override fun onStart() {
        user = interactor.getUserModel()

        viewState.setTitle(R.string.label_tasks)
        viewState.setAdapter()
    }

    override fun onFinish() {
    }

    fun onDateSelected(date: CalendarDay) {
        dateStr = String.format(
            resourceManager.getString(R.string.format_date),
            date.day,
            date.month,
            date.year
        )

        Log.i(TAG, dateStr)

        user?.apply {
            interactor.apply {
                getTasks(id, dateStr)
                    .doOnSubscribe { viewState.showProgressBar(true) }
                    .doFinally { viewState.showProgressBar(false) }
                    .subscribe({
                        Log.i(TAG, "Code:${it.code()}")
                        Log.i(TAG, "Body:${it.body()}")

                        when (it.code()) {
                            AppConstants.STATUS_CODE_200 -> {
                                it?.body()?.items?.apply {
                                    viewState.setList(this)
                                    viewState.setDecor(dimen.toInt())
                                }
                            }
                            else -> {
                                viewState.showMessageError(ResErrorHelper.showErrorMessage(TAG, it))
                            }
                        }

                    }, {
                        viewState.showMessageError(
                            ResErrorHelper.showThrowableMessage(
                                resourceManager,
                                TAG,
                                it
                            )
                        )
                    })
                    .connect()
            }
        }
    }

    fun showDeleteDialog(model: Task, position: Int) {
        viewState.showDeleteDialog(
            R.string.label_delete_task,
            R.string.message_ask_delete_task,
            model,
            position
        )
    }

    fun delete(model: Task, position: Int) {

    }

    fun addTasks(addEdit: Editable?) {
        if (addEdit.isNullOrEmpty()) {
            viewState.showMessageError(R.string.message_error_task_text_is_empty)
            return
        }

        val addStr = addEdit.toString().trim()

        if (addStr.isEmpty()) {
            viewState.showMessageError(R.string.message_error_task_text_is_empty)
            return
        }

        user?.apply {
            params.clear()

            params.put(TaskRequest.user_id, id.toString())
            params.put(TaskRequest.text_name, addStr.trim())
            params.put(TaskRequest.creation_date, dateStr)

            interactor.apply {
                addTasks(params)
                    .doOnSubscribe { viewState.showProgressBar(true) }
                    .doFinally { viewState.showProgressBar(false) }
                    .subscribe({
                        Log.i(TAG, "Code:${it.code()}")
                        Log.i(TAG, "Body:${it.body()}")

                        when (it.code()) {
                            AppConstants.STATUS_CODE_200 -> {
                                it?.body()?.apply {
                                    viewState.addTask(this)
                                    viewState.setDecor(dimen)
                                    viewState.clearAddEdit()
                                    viewState.scrollToLastPosRW()
                                }
                            }
                            else -> {
                                viewState.showMessageError(ResErrorHelper.showErrorMessage(TAG, it))
                            }
                        }
                    }, {
                        viewState.showMessageError(
                            ResErrorHelper.showThrowableMessage(
                                resourceManager,
                                TAG,
                                it
                            )
                        )
                    })
                    .connect()
            }
        }
    }

    fun changeStatus(model: Task, position: Int) {
        val status = if (model.status == 1) 2 else 1

        interactor.apply {
            changeTaskStatus(model.id, status)
                .doOnSubscribe { viewState.showProgressBar(true) }
                .doFinally { viewState.showProgressBar(false) }
                .subscribe({
                    viewState.changeStatusTask(position, status)
                }, {
                    viewState.showMessageError(
                        ResErrorHelper.showThrowableMessage(
                            resourceManager,
                            TAG,
                            it
                        )
                    )
                })
                .connect()
        }
    }
}