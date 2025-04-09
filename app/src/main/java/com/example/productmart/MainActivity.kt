package com.example.productmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productmart.network.NetworkModule
import com.example.productmart.repository.ProductRepositoryImpl
import com.example.productmart.ui.screens.MainScreen
import com.example.productmart.ui.theme.ProductMartTheme
import com.example.productmart.viewmodel.ProductViewModel
import com.example.productmart.viewmodel.ProductViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProductMartTheme {
                val viewModel: ProductViewModel = viewModel(
                    factory = ProductViewModelFactory(ProductRepositoryImpl(NetworkModule.api))
                )
                MainScreen(viewModel = viewModel)
            }
        }
    }
}
