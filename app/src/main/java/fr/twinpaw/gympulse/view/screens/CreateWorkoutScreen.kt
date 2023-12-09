package fr.twinpaw.gympulse.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import fr.twinpaw.gympulse.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import fr.twinpaw.gympulse.model.dataProvider.AuthDataProvider
import fr.twinpaw.gympulse.view.components.DurationPicker
import java.time.Duration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateWorkoutScreen() {

    var workoutName: String by remember { mutableStateOf("") }
    var minutes: Int by remember { mutableIntStateOf(2) }
    var seconds: Int by remember { mutableIntStateOf(30) }
    var showExerciseAlert by remember { mutableStateOf(false) }
    var alertWidth by remember { mutableStateOf(350) }
    var alertHeight by remember { mutableStateOf(350) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    )
    {
        Image(
            painter = painterResource(id = R.drawable.app_background),
            contentDescription = stringResource(id = R.string.app_background_description),
            contentScale = ContentScale.FillBounds,
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = workoutName,
                onValueChange = { workoutName = it },
                label = { Text("Workout Name")},
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            DurationPicker(
                minutes = minutes,
                seconds = seconds,
                minutesChanged = { minutes = it },
                secondsChanged = { seconds = it },
            )

            Button(onClick = { showExerciseAlert = true }) {
                Text("Add exercise")
            }
        }

        if (showExerciseAlert) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .padding(18.dp),
                contentAlignment = Alignment.Center
            ) {
                AddExerciseView(
                    showExerciseAlert = showExerciseAlert,
                    alertWidth = alertWidth,
                    alertHeight = alertHeight,
                    addExercise = { showExerciseAlert = false }
                )
            }
        }
    }
}

@Composable
fun AddExerciseView(
    showExerciseAlert: Boolean,
    alertWidth: Int,
    alertHeight: Int,
    addExercise: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10))
            .background(Color.White)
            .height(alertHeight.dp)
            .width(alertWidth.dp)
            .padding(16.dp)
    ) {
        Column() {
            Button(onClick = addExercise) {
                Text("Add Exercise")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateWorkoutPreview() {
    CreateWorkoutScreen()
}