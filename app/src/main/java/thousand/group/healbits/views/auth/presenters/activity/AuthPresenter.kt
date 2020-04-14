package thousand.group.healbits.views.auth.presenters.activity

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import thousand.group.healbits.global.presentation.BasePresenter
import thousand.group.healbits.global.system.ResourceManager
import thousand.group.healbits.views.auth.interactors.AuthInteractor

@InjectViewState
class AuthPresenter(
    override val context: Context,
    private val resourceManager: ResourceManager,
    private val interactor: AuthInteractor
) : BasePresenter<AuthView>() {

    override fun internetSuccess() {
    }

    override fun internetError() {
    }

    override fun onStart() {
    }

    override fun onFinish() {
    }
}