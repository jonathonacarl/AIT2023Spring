package hu.ait.highlowgamecompose.ui.screen.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.ait.highlowgamecompose.R
import hu.ait.highlowgamecompose.ui.view.SimpleAlertDialog

@Composable
fun GameScreen(
    gameModel: GameViewModel = viewModel()
) {
    val context = LocalContext.current

    var text by rememberSaveable { mutableStateOf("") }
    var textResult by rememberSaveable {
        mutableStateOf(
            context.getString(R.string.text_good_luck)
        )
    }

    var inputErrorState by rememberSaveable { mutableStateOf(false) }
    var errorText by rememberSaveable {
        mutableStateOf(
            context.getString(
                R.string.text_error_default
            )
        )
    }

    var showWinDialog by rememberSaveable { mutableStateOf(false) }

    fun validate(text: String) {
        val allDigit = text.all { char -> char.isDigit() }

        errorText = "This field can be number only"
        inputErrorState = !allDigit
    }

    Column(
        modifier = Modifier.padding(10.dp)
    ) {

        OutlinedTextField(
            value = text,
            label = { Text(stringResource(R.string.text_enter_number)) },
            isError = inputErrorState,
            onValueChange = {
                text = it
                validate(text)
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            trailingIcon = {
                if (inputErrorState)
                    Icon(Icons.Filled.Warning, "error", tint = MaterialTheme.colorScheme.error)
            }
        )
        Row {
            OutlinedButton(
                onClick = {
                    try {
                        val myNum = text.toInt()
                        if (myNum == gameModel.generatedNum) {
                            textResult = context.getString(R.string.text_congrats)

                            showWinDialog = true
                        } else if (myNum < gameModel.generatedNum) {
                            textResult = context.getString(R.string.text_error_higher)
                        } else if (myNum > gameModel.generatedNum) {
                            textResult = "The number is lower"
                        }
                    } catch (e: Exception) {
                        inputErrorState = true
                        errorText = e.localizedMessage
                    }
                }) {
                Text(text = "Guess")
            }

            OutlinedButton(onClick = {
                //generatedNum = Random(System.currentTimeMillis()).nextInt(10)
                gameModel.generateNewNum()
            }) {
                Text(text = "Restart")
            }
        }


        if (inputErrorState) {
            Text(
                text = "$errorText",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Text(
            text = "$textResult",
            color = Color.Blue,
            fontSize = 28.sp
        )

        SimpleAlertDialog(show = showWinDialog,
            onDismiss = { showWinDialog = false },
            onConfirm = { showWinDialog = false }
        )
    }
}