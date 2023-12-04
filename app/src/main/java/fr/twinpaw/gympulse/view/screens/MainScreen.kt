package fr.twinpaw.gympulse.view.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.twinpaw.gympulse.model.dataProvider.AuthDataProvider

@Composable
fun MainScreen(
    authDataProvider: AuthDataProvider,
    goBackToLogin: () -> Unit
) {
    Button(onClick = {
        authDataProvider.isLoggedIn.value = false
        authDataProvider.currentUser.value = null
        goBackToLogin()
    }) {
        Text("Log out")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        authDataProvider = AuthDataProvider(),
        goBackToLogin = {}
    )
}