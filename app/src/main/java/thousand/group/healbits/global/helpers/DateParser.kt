package thousand.group.healbits.global.helpers

import java.text.SimpleDateFormat
import java.util.*

object DateParser {

    internal const val format_YYYY_MM_dd = "yyyy-MM-dd"
    internal const val format_DD_MM_YYYY_With_Dots = "dd.MM.yyyy"
    internal const val format_dd_LLLL = "dd LLLL"
    internal const val format_dd_LLLL_YYYY = "dd LLLL, yyyy"
    internal const val format_YYY_MM_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"
    internal const val format_YYY_MM_HH_MM = "yyyy-MM-dd HH:mm"


    fun stringToDate(dateStr: String, format: String): Date {
        val simpleDateFormat = SimpleDateFormat(format, Locale.US)
        return simpleDateFormat.parse(dateStr)
    }

    fun formatToFullMonthNameCreate(dateStr: String): String {
        val parser = SimpleDateFormat(format_YYYY_MM_dd, Locale.getDefault())
        val formatter = SimpleDateFormat(format_dd_LLLL, Locale.getDefault())
        val output = formatter.format(parser.parse(dateStr))

        return output
    }

    fun convertOneFormatToAnother(
        dateStr: String?,
        parserFormat: String,
        formatterFormat: String
    ): String {

        dateStr?.apply {
            val parser = SimpleDateFormat(parserFormat, Locale.getDefault())
            val formatter = SimpleDateFormat(formatterFormat, Locale.getDefault())
            val output = formatter.format(parser.parse(dateStr))

            return output
        }
        return ""
    }

    fun formatToFullMonthNameWithYearCreate(dateStr: String): String {
        val parser = SimpleDateFormat(format_YYYY_MM_dd, Locale.getDefault())
        val formatter = SimpleDateFormat(format_dd_LLLL_YYYY, Locale.getDefault())
        val output = formatter.format(parser.parse(dateStr))

        return output
    }

    fun formatToDateWithDots(dateStr: String): String {
        val parser = SimpleDateFormat(format_YYYY_MM_dd, Locale.getDefault())
        val formatter = SimpleDateFormat(format_DD_MM_YYYY_With_Dots, Locale.getDefault())
        val output = formatter.format(parser.parse(dateStr))

        return output
    }


}