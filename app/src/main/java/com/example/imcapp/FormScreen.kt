package com.example.imcapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun FormScreen(navController: NavController) {
    Column {
        Text("Primera pantalla")
        Button(
            onClick = {
                navController.navigate(Routes.resultScreen + "/Marco")
            }
        ) {
            Text("Ir a la segunda pantalla")
        }
    }
}