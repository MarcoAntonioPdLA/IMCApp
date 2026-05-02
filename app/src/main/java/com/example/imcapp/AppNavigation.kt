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
        composable(Routes.resultScreen + "/{genderIndex}/{height}/{weight}/{age}") { backStackEntry ->
            val genderIndex = backStackEntry.arguments?.getString("genderIndex")?.toIntOrNull() ?: 0
            val height = backStackEntry.arguments?.getString("height")?.toIntOrNull() ?: 0
            val weight = backStackEntry.arguments?.getString("weight")?.toIntOrNull() ?: 0
            val age = backStackEntry.arguments?.getString("age")?.toIntOrNull() ?: 0
            ResultScreen(navController, genderIndex, height, weight, age)
        }
    })
}
