package thousand.group.healbits.views.auth.presenters.login

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import thousand.group.healbits.R
import thousand.group.healbits.global.constants.simple.AppConstants
import thousand.group.healbits.global.helpers.ResErrorHelper
import thousand.group.healbits.global.presentation.BasePresenter
import thousand.group.healbits.global.system.ResourceManager
import thousand.group.healbits.views.auth.interactors.LoginInteractor
import thousand.group.healbits.views.main.presenters.activity.MainActivity

@InjectViewState
class LoginPresenter(
    override val context: Context,
    private val resourceManager: ResourceManager,
    private val interactor: LoginInteractor
) : BasePresenter<LoginView>() {

    companion object {
        internal const val TAG = "LoginPresenter"
    }

    private var phoneStr: String? = null

    override fun internetSuccess() {
    }

    override fun internetError() {
    }

    override fun onStart() {
    }

    override fun onFinish() {
    }

    fun parsePhone(
        maskFilled: Boolean,
        extractedValue: String,
        formattedValue: String
    ) {
        Log.i(
            TAG,
            "Mask Filled ${maskFilled},  extractedValue ${extractedValue}, formattedValue ${formattedValue}"
        )

        if (maskFilled) {
            setPhone(extractedValue)
        } else {
            setPhone(null)
        }
    }

    fun signInBtnClicked(passEdit: Editable?) {
        passEdit?.apply {

            val password = toString()

            when {
                phoneStr.isNullOrEmpty() -> {
                    viewState.showMessageError(R.string.message_error_phone_is_not_correct)
                }
                password.trim().isEmpty() -> {
                    viewState.showMessageError(R.string.message_error_password_is_empty)
                }
                password.trim().length < 4 -> {
                    viewState.showMessageError(R.string.message_error_password_is_less_than_3)
                }
                else -> {
                    val phoneNumber = String.format(
                        resourceManager.getString(R.string.format_phone_number_clear),
                        phoneStr
                    )

                    Log.i(TAG, "Phone: ${phoneNumber}")
                    Log.i(TAG, "Password: ${password}")

                    interactor.apply {
                        signIn(phoneNumber, password)
                            .doOnSubscribe { viewState.showProgressBar(true) }
                            .doFinally { viewState.showProgressBar(false) }
                            .subscribe({
                                Log.i(TAG, "Code: ${it.code()}")
                                Log.i(TAG, "Body: ${it.body()}")

                                when (it.code()) {
                                    AppConstants.STATUS_CODE_200 -> {
                                        if (!it.body()?.items.isNullOrEmpty()) {
                                            it.body()?.items?.get(0)?.apply {
                                                saveUser(this)
                                                saveUserAccess(true)
                                                openMainActivity()
                                            }
                                        } else {
                                            viewState.showMessageError(R.string.message_error_user_not_found)
                                        }

                                    }
                                    else -> {
                                        viewState.showMessageError(
                                            ResErrorHelper.showErrorMessage(
                                                TAG,
                                                it
                                            )
                                        )
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
        }
    }

    fun setPhone(phone: String?) {
        this.phoneStr = phone
    }

    private fun openMainActivity() {
        val intent = Intent(context, MainActivity::class.java)
        viewState.openMainActivity(intent)
    }
}