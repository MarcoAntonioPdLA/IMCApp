package com.example.imcapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.formScreen, builder = {
        composable(Routes.formScreen) {
            FormScreen(navController)
        }
        composable(Routes.resultScreen + "/{nombre}") {
            val nombre: String? = it.arguments?.getString("nombre")
            ResultScreen(nombre ?: "Sin nombre")
        }
    })
}
