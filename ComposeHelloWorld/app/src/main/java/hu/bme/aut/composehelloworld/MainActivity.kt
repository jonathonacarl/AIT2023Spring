package hu.bme.aut.composehelloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hu.bme.aut.composehelloworld.ui.theme.ComposeHelloWorldTheme
import java.util.Date



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeHelloWorldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Hello AIT")
                }
            }
        }
    }
}



@Composable
fun Greeting(name: String) {
    var timeText by remember {
        mutableStateOf("")
    }


    Column() {
        Text(text = "$timeText")

        Button(
            onClick = {
                timeText = Date(System.currentTimeMillis()).toString()
            }
        ) {
            Text(text = "Show time")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeHelloWorldTheme {
        Greeting("Hello AIT")
    }
}