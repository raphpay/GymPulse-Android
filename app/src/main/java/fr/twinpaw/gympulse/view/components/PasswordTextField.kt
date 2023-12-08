package fr.twinpaw.gympulse.view.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import fr.twinpaw.gympulse.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    text: String,
    isVisible: Boolean,
    onTextChange: (text: String) -> Unit,
    changeVisibility: () -> Unit
) {
    val icon = if (isVisible)
        painterResource(id = R.drawable.eye_fill)
    else
        painterResource(id = R.drawable.eye_slash_fill)

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text(text = "Password") },
        label = { Text(text = "Password") },
        trailingIcon = {
            IconButton(onClick = changeVisibility) {
                Icon(
                    painter = icon,
                    contentDescription = "Visibility Icon"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        textStyle = LocalTextStyle.current.copy(color = Color.Black),
        visualTransformation = if (isVisible) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}