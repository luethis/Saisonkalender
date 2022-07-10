package ch.sluethi.saisonkalender.platform

import java.text.SimpleDateFormat
import java.util.*

actual fun getCurrentMonth(): Int = Calendar.getInstance().get(Calendar.MONTH)

actual fun getMonthAsText(month: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, month)
    return SimpleDateFormat("MMMM", Locale.GERMAN).format(calendar.time)
}
