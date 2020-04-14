package thousand.group.healbits.global.base

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import thousand.group.healbits.R
import thousand.group.healbits.global.helpers.MessageStatusHelper
import thousand.group.healbits.global.helpers.ProgressBarHelper

abstract class BaseActivity : MvpAppCompatActivity(), BaseMvpView {

    companion object {
        private const val PROGRESS_TAG = "fragment_progress"
        private const val TAG = "BaseActivity"
    }

    private var startFlag = false

    private val messageHelper: MessageStatusHelper by inject()
    private val progressHelper: ProgressBarHelper by inject()

    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        Log.i(TAG, "$TAG -> OnCreate")

        messageHelper.setContext(baseContext)
        progressHelper.setContext(baseContext)

        initBundle(intent)
        initView(savedInstanceState)
        initController()

        startFlag = true
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            if (startFlag) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun closeKeyboard(activity: FragmentActivity?) {
        activity?.apply {
            currentFocus?.apply {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(windowToken, 0)
            }
        }
    }

    override fun openKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    override fun showMessageSuccess(message: String) {
        Log.i(TAG, "showMessageSuccess")
        messageHelper.showDialogMessageSuccess(message)
    }

    override fun showMessageSuccess(@StringRes message: Int) {
        Log.i(TAG, "showMessageSuccess")
        messageHelper.showDialogMessageSuccess(message)
    }

    override fun showMessageError(message: String) {
        Log.i(TAG, "showMessageError")
        messageHelper.showDialogMessageError(message)
    }

    override fun showMessageError(@StringRes message: Int) {
        Log.i(TAG, "showMessageError")
        messageHelper.showDialogMessageError(message)
    }

    override fun showMessage(@StringRes message: Int, views: View) =
        Snackbar.make(views, message, Snackbar.LENGTH_SHORT).show()

    override fun showMessage(message: String, views: View) =
        Snackbar.make(views, message, Snackbar.LENGTH_SHORT).show()

    override fun showMessageToast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun showMessageToast(@StringRes message: Int) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun showMessageWithAction(
        @StringRes message: Int, @StringRes actionText: Int, views: View,
        action: () -> Any
    ) {
        val snackBar = Snackbar.make(views, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { action.invoke() }
        snackBar.setActionTextColor(
            ContextCompat.getColor(
                this,
                R.color.colorAccent
            )
        )
        snackBar.show()
    }

    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressHelper.showProgress()
        } else {
            progressHelper.hideProgress()
        }
    }

    fun changeStatusBarColor(colorRess: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            val color = ContextCompat.getColor(this, colorRess)

            val window = window

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (colorRess == R.color.colorPrimaryDark) {
                    window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    window.decorView.systemUiVisibility = 0
                }
            }
            window.statusBarColor = color
        }
    }

    abstract fun initBundle(intent: Intent?)

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initController()

}