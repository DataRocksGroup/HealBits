package thousand.group.healbits.global.extentions

import android.content.DialogInterface
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

internal fun AlertDialog.setPositiiveBtnTextColor(@ColorRes colorRes: Int): AlertDialog {
    val btn = this.getButton(DialogInterface.BUTTON_POSITIVE)
    btn.setTextColor(ContextCompat.getColor(this.context, colorRes))
    return this

}

internal fun AlertDialog.setNegativeBtnTextColor(@ColorRes colorRes: Int): AlertDialog {
    val btn = this.getButton(DialogInterface.BUTTON_NEGATIVE)
    btn.setTextColor(ContextCompat.getColor(this.context, colorRes))
    return this
}