package com.example.sismob.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sismob.ui.login.LoginScreen
import com.example.sismob.ui.login.ScannerScreen
import com.example.sismob.ui.main.MainScreen
import com.example.sismob.ui.splash.SplashScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("main") {
            MainScreen(navController = navController)
        }
        composable("scanner") {
            ScannerScreen(navController = navController)
        }
    }
}
