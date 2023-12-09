package fr.twinpaw.gympulse.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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