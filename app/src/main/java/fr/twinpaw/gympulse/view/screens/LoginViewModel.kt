package fr.twinpaw.gympulse.view.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import fr.twinpaw.gympulse.model.dataProvider.AuthDataProvider
import fr.twinpaw.gympulse.model.services.AuthenticationService
import fr.twinpaw.gympulse.model.services.FirestoreService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordVisibility by mutableStateOf(false)

    fun login(scope: CoroutineScope, authDataProvider: AuthDataProvider, goToMain: () -> Unit) {
        scope.launch {
            try {
                val isAvailable = FirestoreService.shared.checkUserAvailability(email = email)
                if (isAvailable) {
                    FirestoreService.shared.createUser(email = email)
                    val user = AuthenticationService.shared.createUser(email = email, password = password)
                    handleResult(authDataProvider, user, goToMain)
                } else {
                    val user = AuthenticationService.shared.signIn(email = email, password = password)
                    handleResult(authDataProvider, user, goToMain)
                }
            } catch (error: Exception) {
                // TODO: Show a toast for the error
                Log.d("Log_in_button_onclick_error", error.localizedMessage)
            }
        }
    }

    private fun handleResult(authDataProvider: AuthDataProvider, user: FirebaseUser?, goToMain: () -> Unit) {
        if (user !== null) {
            Log.d("Log_in_button_onclick_success", "${user.email}")
            authDataProvider.currentUser = user
            goToMain()
        } else {
            // TODO: Show a toast for the error
            Log.d("Log_in_button_onclick_error", "No user found")
        }
    }
}