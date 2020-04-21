package thousand.group.azimutgas.views.main.presentations.profile

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import thousand.group.healbits.global.presentation.BasePresenter
import thousand.group.healbits.global.system.ResourceManager
import thousand.group.healbits.views.main.interactors.ProfileInteractor

@InjectViewState
class ProfilePresenter(
    override val context: Context,
    private val resourceManager: ResourceManager,
    private val interactor: ProfileInteractor
) : BasePresenter<ProfileView>() {

    companion object {
        internal const val TAG = "ProfilePresenter"
    }

    override fun internetSuccess() {

    }

    override fun internetError() {
    }

    override fun onStart() {
    }

    override fun onFinish() {
    }
}