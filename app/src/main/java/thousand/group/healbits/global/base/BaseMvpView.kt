package thousand.group.healbits.global.base

import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import com.arellomobile.mvp.MvpView

interface BaseMvpView : MvpView {

    fun showMessage(@StringRes message: Int, views: View)

    fun showMessage(message: String, views: View)

    fun showMessageWithAction(
        @StringRes message: Int, @StringRes actionText: Int, views: View,
        action: () -> Any
    )

    fun showMessageToast(message: String)

    fun showMessageToast(@StringRes message: Int)

    fun showProgressBar(show: Boolean)

    fun showMessageSuccess(message: String)

    fun showMessageSuccess(@StringRes message: Int)

    fun showMessageError(message: String)

    fun showMessageError(@StringRes message: Int)

    fun closeKeyboard(activity: FragmentActivity?)

    fun openKeyboard()

}