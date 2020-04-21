package thousand.group.healbits.views.auth.presenters.signup

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.toolbar_title_backspace.*
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import thousand.group.healbits.R
import thousand.group.healbits.global.base.BaseFragment
import thousand.group.healbits.global.constants.scopes.AuthScope
import thousand.group.healbits.global.helpers.MainFragmentHelper

class SignUpFragment : BaseFragment(), SignUpView {

    override val layoutRes = R.layout.fragment_sign_up

    companion object {

        const val TAG = "SignUpFragment"
        var NAV_TAG = MainFragmentHelper.getJsonFragmentTag(
            MainFragmentHelper(
                title = TAG,
                statusBarColorRes = R.color.colorPrimaryDark
            )
        )

        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }

    @InjectPresenter
    lateinit var presenter: SignUpPresenter

    @ProvidePresenter
    fun providePresenter(): SignUpPresenter {
        val scope = getKoin().getOrCreateScope(
            AuthScope.SIGN_UP_SCOPE,
            named(AuthScope.SIGN_UP_SCOPE)
        )

        return scope.get {
            parametersOf(activity)
        }
    }


    override fun initBundle(arguments: Bundle?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initController() {
        back_icon.setOnClickListener {
            activity?.onBackPressed()
        }

        MaskedTextChangedListener.installOn(
            phone_edit_text,
            getString(R.string.format_phone_number),
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {
                    presenter.parsePhone(maskFilled, extractedValue, formattedValue)
                }
            }
        )

        sign_up_btn.setOnClickListener {
            presenter.signUpBtnClicked(
                first_name_edit_text.text,
                last_name_edit_text.text,
                gender_edit_text.text,
                height_edit_text.text,
                weight_edit_text.text,
                pass_edit_text.editText?.text,
                repass_edit_text.editText?.text
            )
        }
    }

    override fun onDestroy() {
        getKoin().getScopeOrNull(AuthScope.SIGN_UP_SCOPE)?.close()
        super.onDestroy()
    }

    override fun setTitle(text: Int) = title_main.setText(text)

    override fun openMainActivity(intent: Intent) {
        activity?.apply {
            startActivity(intent)
            finish()
        }
    }
}