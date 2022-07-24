package ch.sluethi.saisonkalender.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import ch.sluethi.saisonkalender.R

sealed class NavigationItem(val route: String, val title: Int)

sealed class CalendarNavigationItem(
    route: String,
    title: Int,
    val parameter: String? = null
) : NavigationItem(route = route, title = title) {

    object Overview : CalendarNavigationItem(route = "overview", title = R.string.title_calendar)
    object Detail :
        CalendarNavigationItem(
            route = "detail",
            title = R.string.title_detail,
            parameter = "productId"
        ) {
        fun assembleRoute(): String = "$route/{$parameter}"
        fun assembleCall(parameter: String) = "$route/$parameter"
        override fun equals(other: Any?): Boolean {
            return (other as? String)?.contains(route) ?: false
        }
    }
}

sealed class BottomNavigationItem(route: String, title: Int, val icon: ImageVector) :
    NavigationItem(route = route, title = title) {

    object Calendar : BottomNavigationItem(
        route = "calendar",
        title = R.string.title_calendar,
        icon = Icons.Default.List
    )

    object Notification : BottomNavigationItem(
        route = "notifications",
        title = R.string.title_notifications,
        icon = Icons.Default.Notifications
    )
}

val bottomNavigationItems = listOf(
    BottomNavigationItem.Calendar,
    BottomNavigationItem.Notification
)