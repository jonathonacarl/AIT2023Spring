package hu.ait.layoutcomposedemo

import android.graphics.Movie
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.ait.layoutcomposedemo.ui.theme.LayoutComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieScreen()
                }
            }
        }
    }
}

@Composable
fun MovieScreen() {
    Column {
        MovieHead("Movie 1", "2021", R.drawable.movie)
        MovieHead("Movie 2", "2021", R.drawable.movie)
        MovieHead("Movie 3", "2021", R.drawable.movie)
    }
}


@Composable
fun MovieHead(name: String, year: String, imageId: Int) {
    Row (modifier = Modifier.size(200.dp))
    {
        Image(painter = painterResource(id = imageId) ,
            contentDescription = "Movie",
            modifier = Modifier
                .fillMaxHeight()
                .clip(CircleShape)
        )
        Column (
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Blue
                    )
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
                ) {
            Text(text = "$name")
            Text(text = "$year")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LayoutComposeDemoTheme {
        MovieHead("Demo","1987", R.drawable.movie)
    }
}