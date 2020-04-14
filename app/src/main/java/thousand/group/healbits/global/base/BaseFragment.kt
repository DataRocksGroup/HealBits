package thousand.group.healbits.global.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.arellomobile.mvp.MvpAppCompatFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import thousand.group.healbits.R
import thousand.group.healbits.global.helpers.MessageStatusHelper
import thousand.group.healbits.global.helpers.ProgressBarHelper

abstract class BaseFragment : MvpAppCompatFragment(), BaseMvpView {

    companion object {
        private const val PROGRESS_TAG = "fragment_progress"
        private const val TAG = "BaseFragment"
    }

    private val messageHelper: MessageStatusHelper by inject()
    private val progressHelper: ProgressBarHelper by inject()

    abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(layoutRes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messageHelper.setContext(requireContext())
        progressHelper.setContext(requireContext())

        initBundle(arguments)
        initView(savedInstanceState)
        initController()
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
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    override fun showMessageSuccess(message: String) {
        messageHelper.showDialogMessageSuccess(message)
    }

    override fun showMessageSuccess(@StringRes message: Int) {
        Log.i(TAG, "showMessageSuccess")
        messageHelper.showDialogMessageSuccess(message)
    }

    override fun showMessageError(message: String) {
        messageHelper.showDialogMessageError(message)
    }

    override fun showMessageError(@StringRes message: Int) {
        messageHelper.showDialogMessageError(message)
    }

    override fun showMessage(@StringRes message: Int, views: View) =
        Snackbar.make(views, message, Snackbar.LENGTH_SHORT).show()

    override fun showMessage(message: String, views: View) =
        Snackbar.make(views, message, Snackbar.LENGTH_SHORT).show()

    override fun showMessageToast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    override fun showMessageToast(@StringRes message: Int) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    override fun showMessageWithAction(
        @StringRes message: Int, @StringRes actionText: Int, views: View,
        action: () -> Any
    ) {
        val snackBar = Snackbar.make(views, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { action.invoke() }
        snackBar.setActionTextColor(
            ContextCompat.getColor(
                requireContext(),
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

    abstract fun initBundle(arguments: Bundle?)

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initController()

}