package fr.twinpaw.gympulse.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.twinpaw.gympulse.R
import fr.twinpaw.gympulse.model.dataProvider.AuthDataProvider
import fr.twinpaw.gympulse.model.services.AuthenticationService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    authDataProvider: AuthDataProvider,
    goBackToLogin: () -> Unit,
    createWorkout: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.app_background),
            contentDescription = stringResource(id = R.string.app_background_description),
            contentScale = ContentScale.FillBounds,
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.dumbbell),
                contentDescription = stringResource(id = R.string.dumbbell_icon),
                contentScale = ContentScale.FillBounds,
            )

            Text(
                modifier = Modifier.padding(8.dp),
                text = "No workouts",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                modifier = Modifier.padding(8.dp),
                text = "Start creating a workout to use the app",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )

            Button(onClick = { createWorkout() }) {
                Text("Create")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        authDataProvider = AuthDataProvider(),
        goBackToLogin = {},
        createWorkout = {},
    )
}