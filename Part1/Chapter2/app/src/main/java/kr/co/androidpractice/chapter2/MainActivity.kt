package kr.co.androidpractice.chapter2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.androidpractice.chapter2.ui.theme.Chapter2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Conversation()
                }
            }
        }
    }
}

@Composable
fun Conversation() {
    var text by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("0") }
    var inputLabel by remember { mutableStateOf("cm") }
    var outputLabel by remember { mutableStateOf("m") }

    fun convert(value: String, from: String, to: String): String {
        return if(value == "") {
            "0"
        } else {
            val valueDouble = value.toDouble()
            when (from to to) {
                "cm" to "m" -> (valueDouble / 100).toString()
                "m" to "cm" -> (valueDouble * 100).toString()
                else -> "0"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                    result = convert(it, inputLabel, outputLabel)
                },
                placeholder = { Text(text = "숫자를 입력하세요")}
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.size(50.dp, 20.dp),
                text = inputLabel)
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = result)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.size(50.dp, 20.dp),
                text = outputLabel)
        }

        Button(onClick = {
            val temp = inputLabel
            inputLabel = outputLabel
            outputLabel = temp

            result = convert(text, inputLabel, outputLabel)
        }) {
            Text(text = "변환")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chapter2Theme {
        Conversation()
    }
}