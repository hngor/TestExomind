package com.example.testexomind.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun HomeScreen(onDownloadWeatherDataClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenue.")
        Button(onClick = { onDownloadWeatherDataClick() }) {
            Text("Télécharger les données météo.")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen( onDownloadWeatherDataClick = {})
}