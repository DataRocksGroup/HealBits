package thousand.group.healbits.views.main.presenters.activity

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import thousand.group.healbits.global.presentation.BasePresenter
import thousand.group.healbits.global.system.ResourceManager
import thousand.group.healbits.views.main.interactors.MainInteractor

@InjectViewState
class MainPresenter(
    override val context: Context,
    private val resourceManager: ResourceManager,
    private val interactor: MainInteractor
) : BasePresenter<MainView>() {

    override fun internetSuccess() {
    }

    override fun internetError() {
    }

    override fun onStart() {
    }

    override fun onFinish() {
    }
}