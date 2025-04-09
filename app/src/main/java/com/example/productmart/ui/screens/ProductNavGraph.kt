package com.example.productmart.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.productmart.model.Product
import com.example.productmart.ui.screens.DetailScreen
import com.example.productmart.ui.screens.HomeScreen

@Composable
fun ProductNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(onProductClick = { productId ->
                navController.navigate("detail/$productId")
            })
        }
        composable(
            route = "detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            DetailScreen(productId)
        }

    }
}

