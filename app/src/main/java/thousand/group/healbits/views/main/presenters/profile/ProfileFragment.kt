package thousand.group.azimutgas.views.main.presentations.profile

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import thousand.group.healbits.R
import thousand.group.healbits.global.base.BaseFragment
import thousand.group.healbits.global.constants.scopes.MainScope
import thousand.group.healbits.global.helpers.MainFragmentHelper

class ProfileFragment : BaseFragment(), ProfileView {

    override val layoutRes = R.layout.fragment_profile

    companion object {

        const val TAG = "ProfileFragment"

        var NAV_TAG = MainFragmentHelper.getJsonFragmentTag(
            MainFragmentHelper(
                title = TAG,
                isShowBottomNavBar = true
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
    }

    override fun onDestroy() {
        getKoin().getScopeOrNull(MainScope.PROFILE_SCOPE)?.close()
        super.onDestroy()
    }

}