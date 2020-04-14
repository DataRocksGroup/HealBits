package thousand.group.healbits.global.helpers

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import thousand.group.healbits.R

class ProgressBarHelper {
    private var context: Context? = null
    private var progressDialog: AlertDialog? = null

    internal fun setContext(context: Context) {
        this.context = context
    }

    internal fun showProgress() {
        ExecHelper().execute {
            clearDialog()
            createDialog()
        }
    }

    internal fun hideProgress() = clearDialog()

    private fun createDialog() {
        context?.apply {
            val progressAlertDB = AlertDialog.Builder(this)
            val layout =
                LayoutInflater.from(this).inflate(R.layout.layout_dialog_on_progress, null, false)

            progressAlertDB.setView(layout)
            progressDialog = progressAlertDB.create()

            progressDialog?.apply {
                setCancelable(false)
                show()
            }

            progressDialog?.window?.apply {
                val size = context.resources.getDimension(R.dimen._60sdp).toInt()

                setLayout(size, size)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    private fun clearDialog() {
        progressDialog?.apply {
            dismiss()
            progressDialog = null
        }
    }

}