package thousand.group.healbits.views.main.presenters.activity

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
import thousand.group.healbits.global.constants.scopes.MainScope
import thousand.group.healbits.global.helpers.MainFragmentHelper

class MainActivity : BaseActivity(), MainView {
    override val layoutRes = R.layout.activity_main

    companion object {
        const val TAG = "MainActivity"
    }

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        val scope = getKoin().getOrCreateScope(
            MainScope.MAIN_SCOPE,
            named(MainScope.MAIN_SCOPE)
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
        getKoin().getScopeOrNull(MainScope.MAIN_SCOPE)?.close()
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
