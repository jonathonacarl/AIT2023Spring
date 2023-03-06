package hu.bme.aut.composehelloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import hu.bme.aut.composehelloworld.ui.theme.ComposeHelloWorldTheme
import java.sql.Time
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
                    TimeShower()
                }
            }
        }
    }

}


@Composable
fun TimeShower() {
    var currentTime by rememberSaveable {
        mutableStateOf("-")
    }

    Column() {
        Text(text = "Time: $currentTime")
        ClickableText(text = AnnotatedString("Press me"),
            onClick = {
                currentTime = Date(System.currentTimeMillis()).toString()
            } )
        Button(
            onClick = {
                currentTime = Date(System.currentTimeMillis()).toString()
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
        TimeShower()
    }
}
