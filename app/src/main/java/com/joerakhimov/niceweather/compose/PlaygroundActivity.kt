package com.joerakhimov.niceweather.compose

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joerakhimov.niceweather.R
import com.joerakhimov.niceweather.compose.ui.theme.NiceWeatherTheme

class PlaygroundActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NiceWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Text
        Text(text = "Hello $name!")
        Text(
            text = stringResource(id = R.string.app_name),
            fontStyle = FontStyle.Italic,
            color = colorResource(id = R.color.teal_700),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        // Input
        val textValue = remember { mutableStateOf("Initial value") }
        TextField(
            value = textValue.value,
            onValueChange = {
                textValue.value = it
                Log.d("WeatherTag", it)
            },
            label = {
                Text("Label")
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )

        // Button
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.teal_700)),
            border = BorderStroke(
                1.dp,
                color = colorResource(id = R.color.purple_200)
            )
        ){
            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.Red
            )
        }

        // Image
        Icon(painterResource(id=R.drawable.ic_android_black_24dp), contentDescription = "")

        // Progress
        CircularProgressIndicator(
            color = colorResource(id = R.color.purple_200),
            strokeWidth = 5.dp
        )
        LinearProgressIndicator(progress = 0.5f)

        // Dialog
        MyAlertDialog()

    }

}

@Composable
fun MyAlertDialog() {
    val shouldShowDialog = remember { mutableStateOf(true) }
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
            },
            title = { Text(text = stringResource(id = R.string.alert_dialog_title)) },
            text = { Text(text = stringResource(id = R.string.alert_dialog_text)) },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.teal_700)),
                    onClick = {
                        shouldShowDialog.value = false
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.confirm),
                        color = Color.White
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NiceWeatherTheme {
        Greeting("Android")
    }
}