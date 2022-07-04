package ch.sluethi.saisonkalender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import ch.sluethi.saisonkalender.navigation.BottomBar
import ch.sluethi.saisonkalender.navigation.BottomNavGraph

class SaisonActivity : ComponentActivity() {

    private val viewModel: SaisonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(bottomBar = { BottomBar(navController = navController) }) { _ ->
                BottomNavGraph(navHostController = navController, viewModel = viewModel)
            }
        }
    }
}