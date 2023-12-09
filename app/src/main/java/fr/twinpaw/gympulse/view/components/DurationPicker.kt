package fr.twinpaw.gympulse.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DurationPicker(
    minutes: Int,
    seconds: Int,
    minutesChanged: (Int) -> Unit,
    secondsChanged: (Int) -> Unit,
) {
    var isMinutesExpanded by remember { mutableStateOf(false) }
    var isSecondsExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Break Duration",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        NumberPicker(
            isExpanded = isMinutesExpanded,
            isExpandedChanged = { isExpanded ->
                isMinutesExpanded = isExpanded
            }, value = minutes,
            onValueChanged = { minutesChanged(it) },
            label = "Min"
        )

        NumberPicker(
            isExpanded = isSecondsExpanded,
            isExpandedChanged = { isExpanded ->
                isSecondsExpanded = isExpanded
            },
            value = seconds,
            onValueChanged = { secondsChanged(it) },
            label = "Sec"
        )
    }
}
