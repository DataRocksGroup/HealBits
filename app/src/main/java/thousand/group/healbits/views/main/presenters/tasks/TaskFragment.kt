package thousand.group.healbits.views.main.presenters.tasks

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import kotlinx.android.synthetic.main.fragment_task.*
import kotlinx.android.synthetic.main.toolbar_title.*
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import thousand.group.healbits.R
import thousand.group.healbits.global.adapters.TaskAdapter
import thousand.group.healbits.global.base.BaseFragment
import thousand.group.healbits.global.constants.scopes.MainScope
import thousand.group.healbits.global.extentions.visible
import thousand.group.healbits.global.helpers.MainFragmentHelper
import thousand.group.healbits.model.simple.Task
import thousand.group.mybuh.global.decorations.VerticalListItemDecoration

class TaskFragment : BaseFragment(), TaskView {
    override val layoutRes = R.layout.fragment_task

    companion object {

        const val TAG = "TaskFragment"
        var NAV_TAG = MainFragmentHelper.getJsonFragmentTag(
            MainFragmentHelper(
                title = TAG,
                statusBarColorRes = R.color.colorPrimaryDark,
                isShowBottomNavBar = true,
                posBottomNav = 2
            )
        )

        fun newInstance(): TaskFragment {
            val fragment = TaskFragment()
            val arguments = Bundle()
            fragment.arguments = arguments
            return fragment
        }
    }

    @InjectPresenter
    lateinit var presenter: TaskPresenter

    @ProvidePresenter
    fun providePresenter(): TaskPresenter {
        val scope = getKoin().getOrCreateScope(
            MainScope.TASKS_SCOPE,
            named(MainScope.TASKS_SCOPE)
        )

        return scope.get {
            parametersOf(activity)
        }
    }

    private lateinit var adapter: TaskAdapter

    override fun initBundle(arguments: Bundle?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initController() {
        calendarView.setOnDateChangedListener(object : OnDateSelectedListener {
            override fun onDateSelected(
                widget: MaterialCalendarView,
                date: CalendarDay,
                selected: Boolean
            ) {
                presenter.onDateSelected(date)
            }
        })

        add_task.setOnClickListener {
            presenter.addTasks(add_edit.text)
        }
    }

    override fun onDestroy() {
        getKoin().getScopeOrNull(MainScope.TASKS_SCOPE)?.close()
        super.onDestroy()
    }

    override fun setTitle(text: Int) = title_main.setText(text)

    override fun showEmptyText(show: Boolean) {
        task_list.visible(!show)
        text_nothing_found.visible(show)
    }

    override fun setAdapter() {
        if (!::adapter.isInitialized) {
            adapter = TaskAdapter(
                requireContext(),
                { model, position ->
                    presenter.showDeleteDialog(model, position)
                },
                { model, position ->

                },
                {
                    showEmptyText(it)
                }
            )
        }

        task_list.adapter = adapter
    }

    override fun setList(list: MutableList<Task>) {
        if (::adapter.isInitialized) {
            adapter.setDataList(list)
        }
    }

    override fun setDecor(dimen: Int) {
        if (task_list.itemDecorationCount > 0) {
            task_list.removeItemDecorationAt(0)
        }

        task_list.addItemDecoration(
            VerticalListItemDecoration(
                dimen,
                adapter.itemCount
            )
        )
    }

    override fun showDeleteDialog(title: Int, message: Int, model: Task, position: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.label_yes) { dialogInterface, i ->
                presenter.delete(model, position)
                dialogInterface.dismiss()
            }
            .setNegativeButton(R.string.label_no) { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            .setOnCancelListener {
                adapter.closeBasketItem(position)
            }
            .setOnDismissListener {
                adapter.closeBasketItem(position)
            }
            .show()
    }

    override fun deleteItem(position: Int) {
        if (::adapter.isInitialized) {
            adapter.deleteItem(position)
        }
    }

    override fun clearAddEdit() {
        add_edit.setText("")
    }

    override fun scrollToLastPosRW() {
        task_list.scrollToPosition(adapter.itemCount)
    }

    override fun addTask(model: Task) {
        if (::adapter.isInitialized) {
            adapter.addTask(model)
        }
    }

}