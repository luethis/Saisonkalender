package ch.sluethi.saisonkalender.platform

/**
 * TODO: do this with a third party lib
 */

expect fun getCurrentMonth() : Int

fun getCurrentMonthAsText() = getMonthAsText(getCurrentMonth())

expect fun getMonthAsText(month : Int) : String

