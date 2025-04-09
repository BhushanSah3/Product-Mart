package com.example.productmart.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.productmart.ui.screens.*
import com.example.productmart.viewmodel.ProductUiState
import com.example.productmart.viewmodel.ProductViewModel

@Composable
fun ProductNavGraph(
    navController: NavHostController,
    viewModel: ProductViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            when (uiState) {
                is ProductUiState.Loading -> LoadingScreen()
                is ProductUiState.Error -> ErrorScreen((uiState as ProductUiState.Error).message)
                is ProductUiState.Success -> {
                    val products = (uiState as ProductUiState.Success).products

                    HomeScreen(
                        products = products,
                        viewModel = viewModel,
                        onProductClick = { productId ->
                            navController.navigate("detail/$productId")
                        },
                        onCartClick = {
                           // i have done nothing here
                        },
                        selectedBottomTab = "home",
                        onBottomTabChange = {  }
                    )
                }
            }
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
