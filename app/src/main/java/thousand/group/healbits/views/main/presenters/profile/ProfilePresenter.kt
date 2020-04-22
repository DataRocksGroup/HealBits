package thousand.group.azimutgas.views.main.presentations.profile

import android.content.Context
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import thousand.group.healbits.R
import thousand.group.healbits.global.constants.simple.AppConstants
import thousand.group.healbits.global.extentions.formPhoneNumberFormat
import thousand.group.healbits.global.helpers.ResErrorHelper
import thousand.group.healbits.global.presentation.BasePresenter
import thousand.group.healbits.global.system.ResourceManager
import thousand.group.healbits.model.simple.User
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

    private var startLoad = false
    private var model: User? = null

    override fun internetSuccess() {
    }

    override fun internetError() {
    }

    override fun onStart() {
        viewState.setTitle(R.string.label_profile)
        getProfileData()
    }

    override fun onFinish() {
    }

    fun getProfileData() {
        interactor.apply {
            getUser()?.let { user ->
                getProfile(user.id)
                    .doOnSubscribe {
                        if (!startLoad) {
                            viewState.showProgressBar(true)
                        }
                    }
                    .doFinally {
                        viewState.showProgressBar(false)
                        viewState.showSwipe(false)
                    }
                    .subscribe({
                        Log.i(TAG, "Code: ${it.code()}")
                        Log.i(TAG, "Body: ${it.body()}")

                        when (it.code()) {
                            AppConstants.STATUS_CODE_200 -> {
                                if (!it.body()?.items.isNullOrEmpty()) {
                                    it.body()?.items?.get(0)?.apply {
                                        interactor.setUser(user)
                                        setParams(this)
                                    }
                                }
                            }
                            else -> {
                                setParams(user)
                                viewState.showMessageError(ResErrorHelper.showErrorMessage(TAG, it))
                            }
                        }
                    }, {
                        setParams(user)
                        viewState.showMessageError(R.string.message_error_internet_connection)
                    })
                    .connect()
            }
        }
    }

    fun setParams(model: User) {
        this.model = model

        val fullName = String.format(
            resourceManager.getString(R.string.format_fullname),
            model.first_name,
            model.last_name
        )

        val phoneNumber = model.phone.toString().formPhoneNumberFormat()

        val height = String.format(resourceManager.getString(R.string.format_height), model.height)
        val weight = String.format(resourceManager.getString(R.string.format_weight), model.weight)

        viewState.setName(fullName)
        viewState.setPhoneNumber(phoneNumber)
        viewState.setGender(model.gender)
        viewState.setHeight(height)
        viewState.setWeight(weight)
    }

    fun updateOnResume() {
        viewState.setTitle(R.string.label_profile)
        getProfileData()
    }

    fun refresh() {
        getProfileData()
    }

    fun openEditFragment() {
        model?.apply {
            viewState.openEditFragment(id)
        }
    }
}