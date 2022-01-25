package com.example.first_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.first_compose.ui.theme.First_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            First_ComposeTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    var shouldShowOnboarding by remember {
        mutableStateOf(true)
    }

    if (shouldShowOnboarding)
        OnboardingScreen { shouldShowOnboarding = false }
    else
        Greetings()
}

@Composable
fun Greetings(names: List<String> = List(1000) { "$it" }) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn {
            items(items = names) {
                Greeting(it)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Hello $name!", modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            )
            OutlinedButton(onClick = { expanded.value = !expanded.value }) {
                Text(
                    when (expanded.value) {
                        true ->
                            "Show less"
                        else ->
                            "Show more"
                    }
                )
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome onboard")
            Button(
                modifier = Modifier.padding(vertical = 10.dp),
                onClick = onContinueClicked
            ) {
                Text(text = "Continue")
            }
        }

    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 350)
@Composable
fun DefaultPreview() {
    Greetings()
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    First_ComposeTheme() {
        OnboardingScreen(onContinueClicked = {})
    }
}
