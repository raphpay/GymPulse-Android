package fr.twinpaw.gympulse.view.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.twinpaw.gympulse.R
import fr.twinpaw.gympulse.model.services.AuthenticationService
import fr.twinpaw.gympulse.model.services.FirestoreService
import fr.twinpaw.gympulse.view.components.PasswordTextField
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope as rememberCoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(goToMain: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    fun loginButtonTapped() {
        scope.launch {
            try {
                val isAvailable = FirestoreService.shared.checkUserAvailability(email = email)
                if (isAvailable) {
                    FirestoreService.shared.createUser(email = email)
                    val user = AuthenticationService.shared.createUser(email = email, password = password)
                    Log.d("Log_in_button_onclick_success", "${user?.email}")
                    // TODO: Save user in memory
                    goToMain()
                } else {
                    val user = AuthenticationService.shared.signIn(email = email, password = password)
                    Log.d("Log_in_button_onclick_success", "${user?.email}")
                    // TODO: Save user in memory
                    goToMain()
                }
            } catch (error: Exception) {
                // TODO: Show a toast for the error
                Log.d("Log_in_button_onclick_error", error.localizedMessage)
            }
        }
    }

    Box {
        Image(
            painter = painterResource(id = R.drawable.app_background),
            contentDescription = stringResource(id = R.string.app_background_description),
            contentScale = ContentScale.FillBounds,
        )
        
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email")},
                textStyle = LocalTextStyle.current.copy(color = Color.Black)
            )

            PasswordTextField(
                text = password,
                isVisible = passwordVisibility,
                onTextChange = { password = it },
                changeVisibility = { passwordVisibility = !passwordVisibility }
            )

            Button(onClick = {loginButtonTapped()}) {
                Text(stringResource(R.string.log_in_text_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(goToMain = {})
}