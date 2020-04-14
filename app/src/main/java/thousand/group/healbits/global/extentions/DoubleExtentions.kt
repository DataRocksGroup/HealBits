package thousand.group.healbits.global.extentions


fun Double.roundTo(): Double {
    return String.format("%.2f", this).replace(",", ".").toDouble()
}


