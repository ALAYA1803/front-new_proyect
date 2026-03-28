package com.presto.prezto

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.presto.prezto.feature_explore.presentation.home.HomeScreen

@Composable
fun PrestoNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onNavigateToDetail = { itemId ->
                    navController.navigate("item_detail/$itemId")
                }
            )
        }
        composable("item_detail/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
        }
    }
}