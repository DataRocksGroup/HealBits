package thousand.group.healbits.views.main.presenters.profile_edit

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import thousand.group.healbits.global.base.BaseMvpView

@StateStrategyType(OneExecutionStateStrategy::class)
interface ProfileEditView : BaseMvpView {
    fun goBack()
}