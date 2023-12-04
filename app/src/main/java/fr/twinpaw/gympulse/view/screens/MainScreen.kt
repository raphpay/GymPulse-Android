package fr.twinpaw.gympulse.view.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(goBackToLogin: () -> Unit) {
    Button(onClick = goBackToLogin) {
        Text("Log out")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(goBackToLogin = {})
}