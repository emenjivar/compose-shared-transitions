package com.emenjivar.transitions.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emenjivar.transitions.ui.screens.home.HomeRoute
import com.emenjivar.transitions.ui.screens.home.HomeScreen

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeScreen(navController = navController)
        }
    }
}