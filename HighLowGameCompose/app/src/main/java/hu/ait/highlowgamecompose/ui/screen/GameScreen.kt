package hu.ait.highlowgamecompose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GameScreen() {
    Column() {
        OutlinedTextField(value = "",
            onValueChange = {
            },
            modifier = Modifier.fillMaxWidth())
        OutlinedButton(onClick = {}) {
            Text(text = "Guess")
        }
        Text(text = "Result: ")
    }
}