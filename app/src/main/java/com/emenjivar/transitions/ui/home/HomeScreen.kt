package com.emenjivar.transitions.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Composable
fun HomeScreen(
    navController: NavController
) {
    Text(text = "Hello word")
}

@Serializable
object HomeRoute