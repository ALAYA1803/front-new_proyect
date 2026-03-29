package com.presto.prezto

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.presto.prezto.feature_auth.presentation.login.LoginScreen
import com.presto.prezto.feature_auth.presentation.register.RegisterScreen
import com.presto.prezto.feature_explore.presentation.home.HomeScreen
import com.presto.prezto.feature_explore.presentation.item_detail.ItemDetailScreen
import com.presto.prezto.feature_auth.presentation.splash.SplashScreen
import com.presto.prezto.feature_explore.presentation.publish.PublishScreen
import com.presto.prezto.feature_profile.presentation.profile.ProfileScreen

@Composable
fun PrestoNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onNavigateNext = {
                    navController.navigate("login") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable("login") {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(
                onNavigateToDetail = { itemId ->
                    navController.navigate("item_detail/$itemId")
                },
                onNavigateToProfile = {
                    navController.navigate("profile")
                },
                onNavigateToPublish = {
                    navController.navigate("publish")
                }

            )
        }
        composable("item_detail/{itemId}") {
            ItemDetailScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable("profile") {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onLogoutClick = {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        composable("publish") {
            PublishScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}