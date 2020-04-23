package thousand.group.healbits.views.main.presenters.categories

import androidx.annotation.StringRes
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import thousand.group.healbits.global.base.BaseMvpView
import thousand.group.healbits.model.simple.Category

@StateStrategyType(OneExecutionStateStrategy::class)
interface CategoriesView : BaseMvpView {
    fun showSwipe(show: Boolean)

    fun showEmptyText(show: Boolean)

    fun setTitle(@StringRes title: Int)

    fun setAdapter()

    fun setList(list: MutableList<Category>)

    fun setDecor(spaceDimen: Int)
}