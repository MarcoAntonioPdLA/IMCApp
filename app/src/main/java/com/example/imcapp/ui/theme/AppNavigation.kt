package com.example.imcapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imcapp.FormScreen
import com.example.imcapp.ResultScreen
import com.example.imcapp.Routes

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
