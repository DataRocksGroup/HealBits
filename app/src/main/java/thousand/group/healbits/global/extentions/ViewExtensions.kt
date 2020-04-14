package thousand.group.healbits.global.extentions

import android.view.View


fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}
