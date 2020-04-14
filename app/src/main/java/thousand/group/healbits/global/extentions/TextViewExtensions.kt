package thousand.group.healbits.global.extentions

import android.text.Selection
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView

object TV_OBJECT {
    internal val TAG = "TV_EXTENTIONS"
}

internal fun TextView.makeLinks(vararg links: Triple<String, Int, View.OnClickListener>) {
    val spannableString = SpannableString(this.text)
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                link.third.onClick(view)
            }
        }

        val startIndexOfLink = this.text.toString().indexOf(link.first)

        spannableString.setSpan(
            clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            ForegroundColorSpan(link.second),
            startIndexOfLink,
            startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    this.movementMethod =
        LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}

//internal fun TextView.makeRedAsterisk(context: Context?) {
//    context?.let {
//        val asterix = it.resources.getString(R.string.field_asterics)
//
//        val string = this.text.toString().trim()
//        val spannableString = SpannableString(string)
//        val size = string.length
//
//        if (size != 0) {
//            for (i in 1..size) {
//
//                val index = i - 1
//                val charStr = string.get(index).toString()
//
//                if (charStr.equals(asterix)) {
//                    val colorSpan =
//                        ForegroundColorSpan(ContextCompat.getColor(it, R.color.colorRed))
//
//                    spannableString.setSpan(
//                        colorSpan,
//                        index,
//                        i,
//                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//                    )
//                    continue
//                }
//            }
//
//            this.setText(spannableString, TextView.BufferType.SPANNABLE)
//        }
//    }
//}