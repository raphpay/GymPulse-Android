package fr.twinpaw.gympulse.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.twinpaw.gympulse.view.screens.LoginScreen
import fr.twinpaw.gympulse.view.screens.MainScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(goToMain = { navController.navigate("main") })
        }
        composable("main") { MainScreen() }
    }
}