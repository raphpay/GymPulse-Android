package fr.twinpaw.gympulse.view.components

import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun NumberPicker(
    isExpanded: Boolean,
    isExpandedChanged: (Boolean) -> Unit,
    value: Int,
    onValueChanged: (Int) -> Unit,
    label: String,
) {
    Button(onClick = { isExpandedChanged(true) }) {
        Text(value.toString())
    }
    DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpandedChanged(false) }) {
        repeat(60) {
            DropdownMenuItem(
                text = {
                    Text(it.toString())
                },
                onClick = {
                    onValueChanged(it)
                    isExpandedChanged(false)
                }
            )
        }
    }
    Text(label, color = Color.Black)
}