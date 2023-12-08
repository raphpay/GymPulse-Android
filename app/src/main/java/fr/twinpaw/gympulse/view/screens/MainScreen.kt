package fr.twinpaw.gympulse.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.twinpaw.gympulse.model.dataProvider.AuthDataProvider
import fr.twinpaw.gympulse.model.services.AuthenticationService

@Composable
fun MainScreen(
    authDataProvider: AuthDataProvider,
    goBackToLogin: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Welcome ${authDataProvider.currentUser?.email}")

        Button(onClick = {
            AuthenticationService.shared.signOut()
            authDataProvider.logout()
            goBackToLogin()
        }) {
            Text("Log out")
        }
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