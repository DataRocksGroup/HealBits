package thousand.group.azimutgas.views.main.presentations.profile

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar_edit.*
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import thousand.group.healbits.R
import thousand.group.healbits.global.base.BaseFragment
import thousand.group.healbits.global.constants.scopes.MainScope
import thousand.group.healbits.global.extentions.replaceFragmentWithBackStack
import thousand.group.healbits.global.helpers.MainFragmentHelper
import thousand.group.healbits.views.main.presenters.profile_edit.ProfileEditFragment

class ProfileFragment : BaseFragment(), ProfileView {

    override val layoutRes = R.layout.fragment_profile

    companion object {

        const val TAG = "ProfileFragment"

        var NAV_TAG = MainFragmentHelper.getJsonFragmentTag(
            MainFragmentHelper(
                title = TAG,
                isShowBottomNavBar = true,
                posBottomNav = 0
            )
        )

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter(): ProfilePresenter {
        val scope = getKoin().getOrCreateScope(
            MainScope.PROFILE_SCOPE,
            named(MainScope.PROFILE_SCOPE)
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
        swipe_refresh.setOnRefreshListener {
            presenter.refresh()
        }

        edit_icon.setOnClickListener {
            presenter.openEditFragment()
        }

    }

    override fun onDestroy() {
        getKoin().getScopeOrNull(MainScope.PROFILE_SCOPE)?.close()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        presenter.updateOnResume()
    }

    override fun setTitle(text: Int) = title_main.setText(text)

    override fun setName(text: String) {
        name_text.text = text
    }

    override fun setPhoneNumber(text: String) {
        number_layout_text.text = text
    }

    override fun setGender(text: String) {
        gender_text.text = text
    }

    override fun setHeight(text: String) {
        height_text.text = text
    }

    override fun setWeight(text: String) {
        weight_text.text = text
    }

    override fun showSwipe(show: Boolean) {
        swipe_refresh.isRefreshing = show
    }

    override fun openEditFragment(id: Int) {
        activity?.supportFragmentManager?.replaceFragmentWithBackStack(
            R.id.fragment_container,
            ProfileEditFragment.newInstance(id),
            ProfileEditFragment.NAV_TAG
        )
    }

}