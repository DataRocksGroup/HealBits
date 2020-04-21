package thousand.group.healbits.views.auth.presenters.signup

import android.content.Intent
import androidx.annotation.StringRes
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import thousand.group.healbits.global.base.BaseMvpView

@StateStrategyType(OneExecutionStateStrategy::class)
interface SignUpView : BaseMvpView {
    fun setTitle(@StringRes text: Int)

    fun openMainActivity(intent: Intent)
}