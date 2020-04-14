package thousand.group.healbits.global.extentions

internal fun String.formPhoneNumberFormat(): String {
    var phone = ""
    //+7 ([000]) [000]-[00]-[00]

    for (i in 0 until length) {

        when (i) {
            0 -> {
                phone += "${get(i)}"
            }
            1 -> {
                phone += " (${get(i)}"
            }
            4 -> {
                phone += ") ${get(i)}"
            }
            7, 9 -> {
                phone += "-${get(i)}"
            }
            else -> {
                phone += get(i)
            }
        }
    }
    return phone

}

//internal fun String.formCardNumber(): String {
//    val sb = StringBuilder()
//
//    forEachIndexed { index, c ->
//        if ((index % 4) == 0 && index != 0) {
//            sb.append(" ")
//        }
//
//        sb.append(c)
//    }
//
//    return sb.toString()
//}

//internal fun String.formCurrencyTengeFormat(context: Context): String {
//    var currency = ""
//    val cleanDecString = getDecimals()
//    if (cleanDecString.isNotEmpty()) {
//        val formatter = DecimalFormat(context.getString(R.string.format_currency))
//        currency = formatter.format(cleanDecString.toBigInteger())
//        currency = currency.replace(",", " ")
//        currency = "${currency} ${context.getString(R.string.field_valuta)}"
//    }
//
//    return currency
//}
//
//internal fun String.getDecimals(): String {
//    return this.replace("[^\\d]".toRegex(), "")
//}