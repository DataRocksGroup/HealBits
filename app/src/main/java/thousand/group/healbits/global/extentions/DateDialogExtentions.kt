package thousand.group.healbits.global.extentions

import android.app.DatePickerDialog
import android.content.DialogInterface
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

internal fun DatePickerDialog.setPositiiveBtnTextColor(@ColorRes colorRes: Int): DatePickerDialog {
    val btn = this.getButton(DialogInterface.BUTTON_POSITIVE)
    btn.setTextColor(ContextCompat.getColor(this.context, colorRes))
    return this

}

internal fun DatePickerDialog.setNegativeBtnTextColor(@ColorRes colorRes: Int): DatePickerDialog {
    val btn = this.getButton(DialogInterface.BUTTON_NEGATIVE)
    btn.setTextColor(ContextCompat.getColor(this.context, colorRes))
    return this
}