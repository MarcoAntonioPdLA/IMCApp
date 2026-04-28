package com.example.imcapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ResultScreen(nombre: String) {
    Text("Hola, $nombre")
}