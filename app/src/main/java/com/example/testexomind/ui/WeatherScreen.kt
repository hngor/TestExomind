package com.example.testexomind.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testexomind.data.model.Weather
import com.example.testexomind.domain.model.WeatherDataState
import kotlinx.coroutines.delay

@Composable
fun WeatherScreen(viewModel: WeatherScreenViewModel) {

    when (val state = viewModel.uiState) {
        is WeatherDataState.InProgress -> ProgressContent(
            progressValue = state.progress
        )
        is WeatherDataState.WeatherData -> WeatherDataContent(
            weatherDataList = state.data,
            onRetry = { viewModel.getWeatherData() })
    }
}

@Composable
fun WeatherDataContent(weatherDataList: MutableList<Weather>, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(weatherDataList) { data ->
                Text("${data.cityName} - ${data.temperature}°C - ${data.clouds}")
            }
        }
        Button(modifier = Modifier.align(CenterHorizontally), onClick = { onRetry() }) {
            Text("Recommencer")
        }
    }
}

@Composable
fun ProgressContent(progressValue: Int) {
    val messages = listOf(
        "Nous téléchargeons les données…",
        "C’est presque fini…",
        "Plus que quelques secondes avant d’avoir le résultat…"
    )
    var currentMessageIndex by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = currentMessageIndex) {
        delay(6000)
        if (currentMessageIndex >= messages.count() - 1) {
            currentMessageIndex = 0
        } else {
            currentMessageIndex++
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp), verticalArrangement = Arrangement.Bottom
    ) {
        Text(messages[currentMessageIndex])
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp),
            progress = progressValue.toFloat() / 60f
        )
    }
}

@Preview
@Composable
fun WeatherDataContentPreview() {
    WeatherDataContent(weatherDataList = mutableListOf(), onRetry = {})
}

@Preview
@Composable
fun ProgressContentPreview() {
    ProgressContent(progressValue = 20)
}