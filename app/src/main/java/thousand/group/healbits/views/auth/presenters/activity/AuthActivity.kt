package thousand.group.healbits.views.auth.presenters.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import thousand.group.healbits.R
import thousand.group.healbits.global.base.BaseActivity
import thousand.group.healbits.global.constants.scopes.AuthScope
import thousand.group.healbits.global.helpers.MainFragmentHelper

class AuthActivity : BaseActivity(), AuthView {
    override val layoutRes = R.layout.activity_auth

    companion object {
        const val TAG = "AuthActivity"
    }

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @ProvidePresenter
    fun providePresenter(): AuthPresenter {
        val scope = getKoin().getOrCreateScope(
            AuthScope.AUTH_ACTIVITY_SCOPE,
            named(AuthScope.AUTH_ACTIVITY_SCOPE)
        )
        return scope.get {
            parametersOf(this)
        }
    }

    override fun initBundle(intent: Intent?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initController() {

        supportFragmentManager.registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks() {

                override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
                    super.onFragmentStarted(fm, f)

                    f.tag?.apply {
                        Log.i(TAG, this)
                        fragmentLifeCycleController(this)
                    }
                }

                override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
                    super.onFragmentPaused(fm, f)

                    if (!fm.fragments.isNullOrEmpty()) {
                        val tag = fm.fragments[fm.fragments.size - 1].tag

                        tag?.apply {
                            Log.i(TAG, this)
                            fragmentLifeCycleController(this)
                        }
                    }
                }
            },
            true
        )

    }

    override fun onDestroy() {
        getKoin().getScopeOrNull(AuthScope.AUTH_ACTIVITY_SCOPE)?.close()
        super.onDestroy()
    }

    private fun fragmentLifeCycleController(tag: String) {

        val statusBarFlag = MainFragmentHelper.getStatusBarColorRes(tag)

        if (statusBarFlag != null) {
            changeStatusBarColor(statusBarFlag)
        } else {
            changeStatusBarColor(R.color.colorAccent)
        }
    }

}