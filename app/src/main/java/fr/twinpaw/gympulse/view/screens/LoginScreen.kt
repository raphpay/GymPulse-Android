package fr.twinpaw.gympulse.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.twinpaw.gympulse.R
import fr.twinpaw.gympulse.model.dataProvider.AuthDataProvider
import fr.twinpaw.gympulse.view.components.PasswordTextField
import androidx.compose.runtime.rememberCoroutineScope as rememberCoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authDataProvider: AuthDataProvider,
    goToMain: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var viewModel = remember { LoginViewModel() }

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
                value = viewModel.email,
                onValueChange = { viewModel.email = it },
                label = { Text("Email")},
                textStyle = LocalTextStyle.current.copy(color = Color.Black)
            )

            PasswordTextField(
                text = viewModel.password,
                isVisible = viewModel.passwordVisibility,
                onTextChange = { viewModel.password = it },
                changeVisibility = { viewModel.passwordVisibility = !viewModel.passwordVisibility }
            )

            Button(onClick = {
                viewModel.login(
                    scope = scope,
                    authDataProvider = authDataProvider,
                    goToMain = goToMain
                )
            }) {
                Text(stringResource(R.string.log_in_text_button))
            }
            Spacer(modifier = Modifier.height(300.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(authDataProvider = AuthDataProvider(), goToMain = {})
}