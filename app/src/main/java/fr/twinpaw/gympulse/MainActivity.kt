package fr.twinpaw.gympulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.FirebaseApp
import fr.twinpaw.gympulse.model.dataProvider.AuthDataProvider
import fr.twinpaw.gympulse.ui.theme.GymPulseTheme
import fr.twinpaw.gympulse.view.NavigationHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            GymPulseApp()
        }
    }
}

@Composable
fun GymPulseApp() {
    val authDataProvider: AuthDataProvider = viewModel()

    // Check user status when the app is launched
    authDataProvider.checkUserStatus()

    GymPulseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Use your content here
            NavigationHost(authDataProvider = authDataProvider)
        }
    }
}