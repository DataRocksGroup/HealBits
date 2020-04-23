package thousand.group.healbits.views.main.presenters.exercise

import android.content.Intent
import androidx.annotation.StringRes
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import thousand.group.healbits.global.base.BaseMvpView
import thousand.group.healbits.model.simple.Exercise

@StateStrategyType(OneExecutionStateStrategy::class)
interface ExerciseView : BaseMvpView {
    fun showSwipe(show: Boolean)

    fun showEmptyText(show: Boolean)

    fun setTitle(@StringRes title: Int)

    fun setAdapter()

    fun setList(list: MutableList<Exercise>)

    fun setDecor(spaceDimen: Int)

    fun openYouTube(appIntent: Intent, webIntent: Intent)
}