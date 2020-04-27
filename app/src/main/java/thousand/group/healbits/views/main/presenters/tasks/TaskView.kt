package thousand.group.healbits.views.main.presenters.tasks

import androidx.annotation.StringRes
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import thousand.group.healbits.global.base.BaseMvpView
import thousand.group.healbits.model.simple.Task

@StateStrategyType(OneExecutionStateStrategy::class)
interface TaskView : BaseMvpView {

    fun setTitle(@StringRes text: Int)

    fun showEmptyText(show: Boolean)

    fun setAdapter()

    fun setList(list: MutableList<Task>)

    fun setDecor(dimen: Int)

    fun showDeleteDialog(title: Int, message: Int, model: Task, position: Int)

    fun deleteItem(position: Int)

    fun clearAddEdit()

    fun scrollToLastPosRW()

    fun addTask(model: Task)

}