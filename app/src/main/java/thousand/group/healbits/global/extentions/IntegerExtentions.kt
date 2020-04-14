package thousand.group.healbits.global.extentions

import java.text.NumberFormat

val Number.numberFormat: NumberFormat get() = NumberFormat.getInstance()

internal fun Number.formatThousands(): String = numberFormat.format(this)

