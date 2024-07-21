package com.emenjivar.transitions.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.emenjivar.transitions.ui.screens.albumDetail.AlbumDetailRoute
import com.emenjivar.transitions.ui.screens.albumDetail.AlbumDetailScreen
import com.emenjivar.transitions.ui.screens.home.HomeRoute
import com.emenjivar.transitions.ui.screens.home.HomeScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = HomeRoute
        ) {
            composable<HomeRoute>(
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) {
                HomeScreen(
                    navController = navController,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this
                )
            }
            composable<AlbumDetailRoute>(
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None }
            ) {
                AlbumDetailScreen(
                    navController = navController,
                    args = it.toRoute<AlbumDetailRoute>(),
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this
                )
            }
        }
    }
}