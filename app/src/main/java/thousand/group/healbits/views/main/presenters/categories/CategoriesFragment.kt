package thousand.group.healbits.views.main.presenters.categories

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.toolbar_title.*
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import thousand.group.healbits.R
import thousand.group.healbits.global.adapters.CategoryAdapter
import thousand.group.healbits.global.base.BaseFragment
import thousand.group.healbits.global.constants.scopes.MainScope
import thousand.group.healbits.global.extentions.replaceFragmentWithBackStack
import thousand.group.healbits.global.extentions.visible
import thousand.group.healbits.global.helpers.MainFragmentHelper
import thousand.group.healbits.model.simple.Category
import thousand.group.healbits.views.main.presenters.exercise.ExerciseFragment
import thousand.group.mybuh.global.decorations.VerticalListItemDecoration

class CategoriesFragment : BaseFragment(), CategoriesView {
    override val layoutRes = R.layout.fragment_categories

    companion object {

        const val TAG = "CategoriesFragment"

        var NAV_TAG = MainFragmentHelper.getJsonFragmentTag(
            MainFragmentHelper(
                title = TAG,
                isShowBottomNavBar = true,
                posBottomNav = 1,
                statusBarColorRes = R.color.colorPrimaryDark
            )
        )

        fun newInstance(): CategoriesFragment {
            return CategoriesFragment()
        }
    }

    @InjectPresenter
    lateinit var presenter: CategoriesPresenter

    @ProvidePresenter
    fun providePresenter(): CategoriesPresenter {
        val scope = getKoin().getOrCreateScope(
            MainScope.CATEGORIES_SCOPE,
            named(MainScope.CATEGORIES_SCOPE)
        )

        return scope.get {
            parametersOf(activity)
        }
    }

    private lateinit var adapter: CategoryAdapter

    override fun initBundle(arguments: Bundle?) {
    }

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
        category_list.visible(!show)
    }

    override fun setTitle(title: Int) = title_main.setText(title)

    override fun setAdapter() {
        if (!::adapter.isInitialized) {
            adapter = CategoryAdapter(
                requireContext(),
                { position, model ->
                    activity?.supportFragmentManager?.replaceFragmentWithBackStack(
                        R.id.fragment_container,
                        ExerciseFragment.newInstance(model),
                        ExerciseFragment.NAV_TAG
                    )
                },
                {
                    showEmptyText(it)
                }
            )
        }

        category_list.adapter = adapter
    }

    override fun setList(list: MutableList<Category>) {
        if (::adapter.isInitialized) {
            adapter.setData(list)
        }
    }

    override fun setDecor(spaceDimen: Int) {
        if (category_list.itemDecorationCount > 0) {
            category_list.removeItemDecorationAt(0)
        }

        category_list.addItemDecoration(
            VerticalListItemDecoration(
                spaceDimen,
                adapter.itemCount
            )
        )
    }

    override fun onDestroy() {
        getKoin().getScopeOrNull(MainScope.CATEGORIES_SCOPE)?.close()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        presenter.updateOnResume()
    }
}