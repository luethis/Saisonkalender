package ch.sluethi.saisonkalender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import ch.sluethi.saisonkalender.navigation.BottomBar
import ch.sluethi.saisonkalender.navigation.BottomNavGraph
import ch.sluethi.saisonkalender.ui.theme.SaisonkalenderTheme

class SaisonActivity : ComponentActivity() {

    private val viewModel: SaisonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SaisonkalenderTheme {
                Scaffold(bottomBar = { BottomBar(navController = navController) }) { _ ->
                    BottomNavGraph(navHostController = navController, viewModel = viewModel)
                }
            }
        }
    }
}