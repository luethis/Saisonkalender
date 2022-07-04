package ch.sluethi.saisonkalender.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {

    object Calendar : BottomNavItem(
        route = "calendar",
        title = "Calendar",
        icon = Icons.Default.List
    )

    object Notification : BottomNavItem(
        route = "notifications",
        title = "Notifications",
        icon = Icons.Default.Notifications
    )
}



