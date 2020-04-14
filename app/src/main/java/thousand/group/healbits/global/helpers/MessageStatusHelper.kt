package thousand.group.healbits.global.helpers

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import thousand.group.healbits.R
import thousand.group.healbits.global.sealed_classes.MessageStatuses

class MessageStatusHelper {

    private var context: Context? = null

    private var messageDialog: AlertDialog? = null

    private val messageDialogHandler = Handler()
    private val messageDialogRunnable = Runnable {
        messageDialog?.apply {
            if (isShowing) {
                dismiss()
            }
        }
    }

    internal inline fun <reified T> showDialogMessageSuccess(message: T) {
        context?.apply {
            showMessageDialog(
                checkMessageType(message),
                MessageStatuses.ON_SUCCESS
            )
        }
    }

    internal inline fun <reified T> showDialogMessageError(message: T) {
        context?.apply {
            showMessageDialog(
                checkMessageType(message),
                MessageStatuses.ON_ERROR
            )
        }
    }

    private inline fun <reified T> checkMessageType(message: T): String {
        var messageStr = ""

        when (T::class) {
            Int::class -> {
                messageStr = context!!.getString(message as Int)
            }
            String::class -> {
                messageStr = message as String
            }
        }

        return messageStr
    }


    private fun setCurrentMessageDialogParams(
        message: String,
        @LayoutRes layoutRes: Int
    ) {
        val layout = LayoutInflater.from(context).inflate(layoutRes, null, false)

        clearMessageDialogParams()
        setMessageDialogParams(layout)

        layout.findViewById<TextView>(R.id.text_message).text = message
    }


    private fun showMessageDialog(message: String, mode: MessageStatuses) {
        ExecHelper().execute {
            when (mode) {
                is MessageStatuses.ON_SUCCESS -> {
                    setCurrentMessageDialogParams(message, R.layout.layout_dialog_on_success)
                }
                is MessageStatuses.ON_ERROR -> {
                    setCurrentMessageDialogParams(message, R.layout.layout_dialog_on_error)
                }
            }
        }
    }

    private fun clearMessageDialogParams() {
        messageDialog?.apply {
            if (isShowing) {
                dismiss()
                messageDialogHandler.removeCallbacks(messageDialogRunnable)
                messageDialog = null
            }
        }
    }

    private fun setMessageDialogParams(view: View) {
        val messageDialogBuilder = AlertDialog.Builder(context!!)
        messageDialogBuilder.setView(view)
        messageDialog = messageDialogBuilder.create()
        messageDialog?.apply {
            setCancelable(false)
            show()
        }

        messageDialog?.window?.apply {

            val width = (context.resources.displayMetrics.widthPixels * .6).toInt()

            setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        messageDialogHandler.postDelayed(messageDialogRunnable, 1000)
    }

    fun setContext(context: Context) {
        this.context = context
    }
}