package ch.sluethi.saisonkalender.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ch.sluethi.saisonkalender.calendar.CalendarScreen
import ch.sluethi.saisonkalender.calendar.CalendarViewModel
import ch.sluethi.saisonkalender.calendar.DetailScreen
import ch.sluethi.saisonkalender.notifications.NotificationsScreen

@Composable
fun NavigationGraph(navHostController: NavHostController, viewModel: CalendarViewModel) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavigationItem.Calendar.route
    ) {
        navigation(
            route = BottomNavigationItem.Calendar.route,
            startDestination = CalendarNavigationItem.Overview.route
        ) {
            composable(CalendarNavigationItem.Overview.route) {
                CalendarScreen(navHostController = navHostController, viewModel = viewModel)
            }
            composable(CalendarNavigationItem.Detail.assembleRoute()) { backStackEntry ->
                DetailScreen(
                    navHostController = navHostController,
                    id = backStackEntry.arguments!!.getString(CalendarNavigationItem.Detail.parameter)!!
                )
            }
        }
        composable(route = BottomNavigationItem.Notification.route) {
            NotificationsScreen()
        }
    }
}