package ch.sluethi.saisonkalender.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import ch.sluethi.saisonkalender.SaisonViewModel
import ch.sluethi.saisonkalender.notifications.NotificationsScreen

val bottomNavigationItems = listOf(
    BottomNavItem.Calendar,
    BottomNavItem.Notification
)

@Composable
fun BottomNavGraph(navHostController: NavHostController, viewModel: SaisonViewModel) {
    NavHost(navController = navHostController, startDestination = BottomNavItem.Calendar.route) {
        composable(route = BottomNavItem.Calendar.route) {
            CalendarNavGraph(viewModel = viewModel)
        }
        composable(route = BottomNavItem.Notification.route) {
            NotificationsScreen()
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                label = { Text(text = item.title) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                onClick = { navController.navigate(item.route) })
        }
    }
}