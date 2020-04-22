package thousand.group.azimutgas.views.main.presentations.profile

import androidx.annotation.StringRes
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import thousand.group.healbits.global.base.BaseMvpView

@StateStrategyType(OneExecutionStateStrategy::class)
interface ProfileView : BaseMvpView {

    fun setTitle(@StringRes text: Int)

    fun setName(text: String)

    fun setPhoneNumber(text: String)

    fun setGender(text: String)

    fun setHeight(text: String)

    fun setWeight(text: String)

    fun showSwipe(show: Boolean)

    fun openEditFragment(id: Int)
}