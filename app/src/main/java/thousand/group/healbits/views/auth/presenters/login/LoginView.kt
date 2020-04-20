package thousand.group.healbits.views.auth.presenters.login

import android.content.Intent
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import thousand.group.healbits.global.base.BaseMvpView

@StateStrategyType(OneExecutionStateStrategy::class)
interface LoginView : BaseMvpView {
    fun openMainActivity(intent: Intent)
}