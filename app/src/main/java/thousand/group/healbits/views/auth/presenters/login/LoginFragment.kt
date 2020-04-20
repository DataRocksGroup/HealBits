package thousand.group.healbits.views.auth.presenters.login

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import thousand.group.healbits.R
import thousand.group.healbits.global.base.BaseFragment
import thousand.group.healbits.global.constants.scopes.AuthScope
import thousand.group.healbits.global.helpers.MainFragmentHelper

class LoginFragment : BaseFragment(), LoginView {
    override val layoutRes = R.layout.fragment_login

    companion object {

        const val TAG = "LoginFragment"
        var NAV_TAG = MainFragmentHelper.getJsonFragmentTag(
            MainFragmentHelper(
                title = TAG,
                statusBarColorRes = R.color.colorPrimaryDark
            )
        )

        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter(): LoginPresenter {
        val scope = getKoin().getOrCreateScope(
            AuthScope.LOGIN_SCOPE,
            named(AuthScope.LOGIN_SCOPE)
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
        main_layout.setOnClickListener { }

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

        login_btn.setOnClickListener {
            presenter.signInBtnClicked(pass_edit_text.editText?.text)
        }

//        sign_up_btn.setOnClickListener {
//            activity?.supportFragmentManager?.replaceFragmentWithBackStack(
//                R.id.fragment_container,
//                SignUpFragment.newInstance(),
//                SignUpFragment.NAV_TAG
//            )
//        }
    }

    override fun onDestroy() {
        getKoin().getScopeOrNull(AuthScope.LOGIN_SCOPE)?.close()
        super.onDestroy()
    }

    override fun openMainActivity(intent: Intent) {
        activity?.apply {
            startActivity(intent)
            finish()
        }
    }

}