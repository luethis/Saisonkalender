package ch.sluethi.saisonkalender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ch.sluethi.saisonkalender.calendar.CalendarViewModel
import ch.sluethi.saisonkalender.components.BottomNavigation
import ch.sluethi.saisonkalender.navigation.NavigationGraph
import ch.sluethi.saisonkalender.ui.theme.SaisonkalenderTheme

class SaisonActivity : ComponentActivity() {

    private val viewModel: CalendarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SaisonkalenderTheme {
                Scaffold(
                    bottomBar = { BottomNavigation(navController = navController) }) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        NavigationGraph(navHostController = navController, viewModel = viewModel)
                    }
                }
            }
        }
    }
}