package ch.sluethi.saisonkalender.navigation

sealed class CalendarNavItem(val route: String, val parameter: String? = null) {
    object Overview : CalendarNavItem(route = "overview")
    object Detail : CalendarNavItem(route = "detail", parameter = "productId") {
        fun assembleRoute(): String = "$route/{$parameter}"
        fun assembleCall(parameter: String) = "$route/$parameter"
    }
}