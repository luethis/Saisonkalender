package ch.sluethi.saisonkalender.helper

expect fun getCurrentMonth() : Int

expect fun getMonthAsText(month : Int) : String

fun getCurrentMonthText() = getMonthAsText(getCurrentMonth())
