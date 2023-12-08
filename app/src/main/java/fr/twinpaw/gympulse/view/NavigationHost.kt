package fr.twinpaw.gympulse.view

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import fr.twinpaw.gympulse.model.dataProvider.AuthDataProvider
import fr.twinpaw.gympulse.view.screens.CreateWorkoutScreen
import fr.twinpaw.gympulse.view.screens.LoginScreen
import fr.twinpaw.gympulse.view.screens.MainScreen

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavigationHost(authDataProvider: AuthDataProvider) {
    val navController = rememberNavController()
    val START = "START"
    val MAIN_SCREEN = "MAIN_SCREEN"
    val CREATE_WORKOUT_SCREEN = "CREATE_WORKOUT_SCREEN"

    NavHost(navController = navController, startDestination = START) {
        composable("start") {
            val isLoggedIn = authDataProvider.isLoggedIn
            if (isLoggedIn) {
                MainScreen(
                    authDataProvider = authDataProvider,
                    goBackToLogin = { navController.popBackStack() },
                    createWorkout = { navController.navigate(CREATE_WORKOUT_SCREEN)})
            } else {
                LoginScreen(
                    authDataProvider = authDataProvider,
                    goToMain = { navController.navigate(MAIN_SCREEN) }
                )
            }
        }
        composable(MAIN_SCREEN) {
            MainScreen(
                authDataProvider = authDataProvider,
                goBackToLogin = { navController.popBackStack() },
                createWorkout = { navController.navigate(CREATE_WORKOUT_SCREEN)}
            )
        }
        composable(CREATE_WORKOUT_SCREEN) {
             CreateWorkoutScreen()
        }
    }
}
