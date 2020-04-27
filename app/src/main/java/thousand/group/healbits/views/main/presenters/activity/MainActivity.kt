package thousand.group.healbits.views.main.presenters.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import thousand.group.azimutgas.views.main.presentations.profile.ProfileFragment
import thousand.group.healbits.R
import thousand.group.healbits.global.base.BaseActivity
import thousand.group.healbits.global.constants.scopes.MainScope
import thousand.group.healbits.global.extentions.clearAndReplaceFragment
import thousand.group.healbits.global.extentions.visible
import thousand.group.healbits.global.helpers.MainFragmentHelper
import thousand.group.healbits.views.main.presenters.categories.CategoriesFragment
import thousand.group.healbits.views.main.presenters.tasks.TaskFragment
import thousand.group.mybuh.global.extentions.setChecked
import thousand.group.mybuh.global.extentions.uncheckAllItems

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
        openProfileFragment()
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

        bottomNavMain.setOnNavigationItemSelectedListener {
            supportFragmentManager.apply {

                val fragment: Fragment?

                if (!this.fragments.isNullOrEmpty()) {
                    fragment = fragments[fragments.size - 1]
                } else {
                    fragment = null
                }

                when (it.itemId) {
                    R.id.navigation_profile -> {
                        if (fragment !is ProfileFragment) {
                            supportFragmentManager.clearAndReplaceFragment(
                                R.id.fragment_container,
                                ProfileFragment.newInstance(),
                                ProfileFragment.NAV_TAG
                            )
                        }
                    }

                    R.id.navigation_category -> {
                        if (fragment !is CategoriesFragment) {
                            supportFragmentManager.clearAndReplaceFragment(
                                R.id.fragment_container,
                                CategoriesFragment.newInstance(),
                                CategoriesFragment.NAV_TAG
                            )
                        }
                    }

                    R.id.navigation_tasks -> {
                        if (fragment !is TaskFragment) {
                            supportFragmentManager.clearAndReplaceFragment(
                                R.id.fragment_container,
                                TaskFragment.newInstance(),
                                TaskFragment.NAV_TAG
                            )
                        }
                    }
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

    }

    override fun onDestroy() {
        getKoin().getScopeOrNull(MainScope.MAIN_SCOPE)?.close()
        super.onDestroy()
    }

    private fun fragmentLifeCycleController(tag: String) {

        val bottomNavVisiblityFlag = MainFragmentHelper.isShowBottomNavBar(tag)
        val bottomNavPosFlag = MainFragmentHelper.getBottomNavPos(tag)
        val statusBarFlag = MainFragmentHelper.getStatusBarColorRes(tag)

        bottomNavMain.visible(bottomNavVisiblityFlag)

        if (bottomNavPosFlag != null) {
            bottomNavMain.setChecked(bottomNavPosFlag)
        } else {
            bottomNavMain.uncheckAllItems()
        }

        if (statusBarFlag != null) {
            changeStatusBarColor(statusBarFlag)
        } else {
            changeStatusBarColor(R.color.colorAccent)
        }
    }

    override fun openProfileFragment() {
        supportFragmentManager.clearAndReplaceFragment(
            R.id.fragment_container,
            ProfileFragment.newInstance(),
            ProfileFragment.NAV_TAG
        )
    }

}
