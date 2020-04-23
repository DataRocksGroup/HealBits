package thousand.group.healbits.views.main.presenters.exercise

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_exercise.*
import kotlinx.android.synthetic.main.toolbar_title.*
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import thousand.group.healbits.R
import thousand.group.healbits.global.adapters.ExerciseAdapter
import thousand.group.healbits.global.base.BaseFragment
import thousand.group.healbits.global.constants.scopes.MainScope
import thousand.group.healbits.global.constants.simple.AppConstants
import thousand.group.healbits.global.extentions.visible
import thousand.group.healbits.global.helpers.MainFragmentHelper
import thousand.group.healbits.model.simple.Category
import thousand.group.healbits.model.simple.Exercise
import thousand.group.mybuh.global.decorations.VerticalListItemDecoration


class ExerciseFragment : BaseFragment(), ExerciseView {
    override val layoutRes = R.layout.fragment_exercise

    companion object {

        const val TAG = "ExerciseFragment"

        var NAV_TAG = MainFragmentHelper.getJsonFragmentTag(
            MainFragmentHelper(
                title = TAG,
                isShowBottomNavBar = false,
                statusBarColorRes = R.color.colorPrimaryDark
            )
        )

        fun newInstance(model: Category): ExerciseFragment {
            val fragment = ExerciseFragment()
            val args = Bundle()
            args.putParcelable(AppConstants.BUNDLE_MODEL, model)
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var presenter: ExercisePresenter

    @ProvidePresenter
    fun providePresenter(): ExercisePresenter {
        val scope = getKoin().getOrCreateScope(
            MainScope.EXERCISE_SCOPE,
            named(MainScope.EXERCISE_SCOPE)
        )

        return scope.get {
            parametersOf(activity)
        }
    }

    private lateinit var adapter: ExerciseAdapter

    override fun initBundle(arguments: Bundle?) = presenter.parseBundle(arguments)

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initController() {
        swipe_refresh.setOnRefreshListener {
            presenter.refresh()
        }
    }

    override fun showSwipe(show: Boolean) {
        swipe_refresh.isRefreshing = show
    }

    override fun showEmptyText(show: Boolean) {
        empty_text.visible(show)
        exercise_list.visible(!show)
    }

    override fun setTitle(title: Int) = title_main.setText(title)

    override fun setAdapter() {
        if (!::adapter.isInitialized) {
            adapter = ExerciseAdapter(
                requireContext(),
                { position, model ->
                    presenter.parseListItem(model)
                },
                {
                    showEmptyText(it)
                }
            )
        }

        exercise_list.adapter = adapter
    }

    override fun setList(list: MutableList<Exercise>) {
        if (::adapter.isInitialized) {
            adapter.setData(list)
        }
    }

    override fun setDecor(spaceDimen: Int) {
        if (exercise_list.itemDecorationCount > 0) {
            exercise_list.removeItemDecorationAt(0)
        }

        exercise_list.addItemDecoration(
            VerticalListItemDecoration(
                spaceDimen,
                adapter.itemCount
            )
        )
    }

    override fun openYouTube(appIntent: Intent, webIntent: Intent) {
        try {
            startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
        }
    }

    override fun onDestroy() {
        getKoin().getScopeOrNull(MainScope.EXERCISE_SCOPE)?.close()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        presenter.updateOnResume()
    }
}