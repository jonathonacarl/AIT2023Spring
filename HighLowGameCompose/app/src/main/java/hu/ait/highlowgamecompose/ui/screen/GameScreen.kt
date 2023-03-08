package hu.ait.highlowgamecompose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.ait.highlowgamecompose.R
import java.util.*

@Composable
fun GameScreen(
    gameModel: GameViewModel = viewModel()
) {
    val context = LocalContext.current


    var text by rememberSaveable { mutableStateOf("") }
    var textResult by rememberSaveable { mutableStateOf(
        context.getString(R.string.text_good_luck)) }

    var inputErrorState by rememberSaveable { mutableStateOf(false) }
    var errorText by rememberSaveable { mutableStateOf(context.getString(
        R.string.text_error_default)) }

    fun validate(text: String) {
        val allDigit = text.all{ char -> char.isDigit() }

        errorText = "This field can be number only"
        inputErrorState = !allDigit
    }

    Column(
        modifier = Modifier.padding(10.dp)
    ) {


        OutlinedTextField(
            value = text,
            label = { Text(stringResource(R.string.text_enter_number)) },
            onValueChange = {
                text = it
                validate(text)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            OutlinedButton(
                onClick = {
                    try {
                        val myNum = text.toInt()
                        if (myNum == gameModel.generatedNum) {
                            textResult = context.getString(R.string.text_congrats)
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

        Text(text = "$textResult",
            color = Color.Blue,
            fontSize = 28.sp
            )
    }
}