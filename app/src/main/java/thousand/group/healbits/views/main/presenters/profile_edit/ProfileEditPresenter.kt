package thousand.group.healbits.views.main.presenters.profile_edit

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import thousand.group.healbits.R
import thousand.group.healbits.global.constants.requests.UserRequest
import thousand.group.healbits.global.constants.simple.AppConstants
import thousand.group.healbits.global.helpers.ResErrorHelper
import thousand.group.healbits.global.presentation.BasePresenter
import thousand.group.healbits.global.system.ResourceManager
import thousand.group.healbits.views.main.interactors.ProfileEditInteractor

@InjectViewState
class ProfileEditPresenter(
    override val context: Context,
    private val resourceManager: ResourceManager,
    private val interactor: ProfileEditInteractor
) : BasePresenter<ProfileEditView>() {

    private var id: Int = 0
    private var params = mutableMapOf<String, String>()

    override fun internetSuccess() {

    }

    override fun internetError() {
    }

    override fun onStart() {
    }

    override fun onFinish() {
    }

    fun parseBundle(args: Bundle?) {
        args?.apply {
            id = getInt(AppConstants.BUNDLE_ID, 0)
        }
    }

    fun signUpBtnClicked(
        firstNameEdit: Editable?,
        lastNameEdit: Editable?,
        genderEdit: Editable?,
        heightEdit: Editable?,
        weightEdit: Editable?,
        passEdit: Editable?,
        rePass: Editable?
    ) {

        Log.i(TAG, firstNameEdit.toString())
        Log.i(TAG, lastNameEdit.toString())
        Log.i(TAG, genderEdit.toString())
        Log.i(TAG, heightEdit.toString())
        Log.i(TAG, weightEdit.toString())
        Log.i(TAG, passEdit.toString())
        Log.i(TAG, rePass.toString())

        val firstNameStr =
            if (!firstNameEdit.isNullOrEmpty()) firstNameEdit.toString().trim() else null

        val lastNameStr =
            if (!lastNameEdit.isNullOrEmpty()) lastNameEdit.toString().trim() else null

        val genderStr =
            if (!genderEdit.isNullOrEmpty()) genderEdit.toString().trim() else null

        val heightStr =
            if (!heightEdit.isNullOrEmpty()) heightEdit.toString().trim() else null

        val weightStr =
            if (!weightEdit.isNullOrEmpty()) weightEdit.toString().trim() else null

        when {
            passEdit.isNullOrEmpty() || rePass.isNullOrEmpty() -> {
                Log.i(TAG, "Error:2")
                viewState.showMessageError(R.string.message_error_password_is_not_correct)
            }
            passEdit.trim().length < 3 -> {
                Log.i(TAG, "Error:3")
                viewState.showMessageError(R.string.message_error_password_is_less_than_3)
            }
            !passEdit.toString().trim().equals(rePass.toString().trim()) -> {
                Log.i(TAG, "Error:4")
                viewState.showMessageError(R.string.message_error_password_is_not_correct)
            }
            firstNameStr.isNullOrEmpty() || lastNameStr.isNullOrEmpty() || genderStr.isNullOrEmpty() || heightStr.isNullOrEmpty() || weightStr.isNullOrEmpty() -> {
                Log.i(TAG, "Error:4")
                viewState.showMessageError(R.string.message_error_data_filled_incorrect)
            }
            else -> {
                Log.i(TAG, "Success")

                createParams(
                    passEdit.trim().toString(),
                    firstNameStr,
                    lastNameStr,
                    genderStr,
                    heightStr,
                    weightStr
                )
                signUp()
            }
        }
    }

    private fun createParams(
        password: String,
        firstName: String,
        lastName: String,
        gender: String,
        height: String,
        weight: String
    ) {
        params.clear()

        params.put(UserRequest.password, password)
        params.put(UserRequest.first_name, firstName)
        params.put(UserRequest.last_name, lastName)
        params.put(UserRequest.gender, gender)
        params.put(UserRequest.height, height)
        params.put(UserRequest.weight, weight)
    }

    private fun signUp() {
        interactor.apply {
            edit(id, params)
                .doOnSubscribe { viewState.showProgressBar(true) }
                .doFinally { viewState.showProgressBar(false) }
                .subscribe({
                    Log.i(TAG, "Code:${it.code()}")
                    Log.i(TAG, "Body:${it.body()}")

                    when (it.code()) {
                        AppConstants.STATUS_CODE_200 -> {
                            viewState.goBack()
                        }
                        else -> {
                            viewState.showMessageError(R.string.message_error_user_is_previously_signed_up)
                        }
                    }

                }, {
                    viewState.showMessageError(
                        ResErrorHelper.showThrowableMessage(
                            resourceManager,
                            TAG,
                            it
                        )
                    )
                })
                .connect()
        }
    }
}