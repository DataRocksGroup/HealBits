package thousand.group.healbits.views.main.presenters.profile_edit

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_profile_edit.*
import kotlinx.android.synthetic.main.toolbar_title_backspace.*
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import thousand.group.healbits.R
import thousand.group.healbits.global.base.BaseFragment
import thousand.group.healbits.global.constants.scopes.MainScope
import thousand.group.healbits.global.constants.simple.AppConstants
import thousand.group.healbits.global.helpers.MainFragmentHelper

class ProfileEditFragment : BaseFragment(), ProfileEditView {
    override val layoutRes = R.layout.fragment_profile_edit

    companion object {

        const val TAG = "ProfileEditFragment"
        var NAV_TAG = MainFragmentHelper.getJsonFragmentTag(
            MainFragmentHelper(
                title = TAG,
                statusBarColorRes = R.color.colorPrimaryDark,
                isShowBottomNavBar = false
            )
        )

        fun newInstance(id: Int): ProfileEditFragment {
            val fragment = ProfileEditFragment()
            val arguments = Bundle()
            arguments.putInt(AppConstants.BUNDLE_ID, id)
            fragment.arguments = arguments
            return fragment
        }
    }

    @InjectPresenter
    lateinit var presenter: ProfileEditPresenter

    @ProvidePresenter
    fun providePresenter(): ProfileEditPresenter {
        val scope = getKoin().getOrCreateScope(
            MainScope.PROFILE_EDIT_SCOPE,
            named(MainScope.PROFILE_EDIT_SCOPE)
        )

        return scope.get {
            parametersOf(activity)
        }
    }


    override fun initBundle(arguments: Bundle?) = presenter.parseBundle(arguments)

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initController() {
        back_icon.setOnClickListener {
            activity?.onBackPressed()
        }

        change_pass.setOnClickListener {
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
        getKoin().getScopeOrNull(MainScope.PROFILE_EDIT_SCOPE)?.close()
        super.onDestroy()
    }

    override fun goBack() {
        activity?.onBackPressed()
    }
}