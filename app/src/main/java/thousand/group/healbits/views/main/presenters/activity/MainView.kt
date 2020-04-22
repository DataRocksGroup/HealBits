package thousand.group.healbits.views.main.presenters.activity

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import thousand.group.healbits.global.base.BaseMvpView

@StateStrategyType(OneExecutionStateStrategy::class)
interface MainView : BaseMvpView {
    fun openProfileFragment()
}