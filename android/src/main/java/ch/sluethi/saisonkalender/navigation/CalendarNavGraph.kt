package ch.sluethi.saisonkalender.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ch.sluethi.saisonkalender.SaisonViewModel
import ch.sluethi.saisonkalender.calendar.DetailScreen
import ch.sluethi.saisonkalender.calendar.OverviewScreen

@Composable
fun CalendarNavGraph(viewModel: SaisonViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = CalendarNavItem.Overview.route) {
        composable(CalendarNavItem.Overview.route) {
            OverviewScreen(viewModel = viewModel, navHostController = navController)
        }
        composable(CalendarNavItem.Detail.assembleRoute()) { backStackEntry ->
            DetailScreen(backStackEntry.arguments!!.getString(CalendarNavItem.Detail.parameter)!!)
        }
    }
}