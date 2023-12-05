package fr.twinpaw.gympulse.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.twinpaw.gympulse.model.dataProvider.AuthDataProvider
import fr.twinpaw.gympulse.view.screens.LoginScreen
import fr.twinpaw.gympulse.view.screens.MainScreen

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavigationHost(authDataProvider: AuthDataProvider) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            val isLoggedIn = authDataProvider.isLoggedIn
            if (isLoggedIn) {
                MainScreen(
                    authDataProvider = authDataProvider,
                    goBackToLogin = { navController.popBackStack() })
            } else {
                LoginScreen(
                    authDataProvider = authDataProvider,
                    goToMain = { navController.navigate("main") }
                )
            }
        }
        composable("main") {
            MainScreen(
                authDataProvider = authDataProvider,
                goBackToLogin = { navController.popBackStack() })
        }
    }
}
