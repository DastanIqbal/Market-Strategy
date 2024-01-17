package com.dastanapps.marketstrategy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dastanapps.marketstrategy.ui.screens.main.HomeScreen
import com.dastanapps.marketstrategy.ui.screens.order.OrderScreen
import com.dastanapps.marketstrategy.viewmodels.MainViewModel

/**
 *
 * Created by Iqbal Ahmed on 17/01/2024
 *
 */


@Composable
fun Navigation(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    modifier: Modifier,
    startDestination: String = "home"
) {
    NavHost(navController, startDestination) {
        composable(route = "home") {
            HomeScreen(mainViewModel.futureOptionState, modifier)
            mainViewModel.showHistory = {
                navController.navigate("orders")
            }
        }
        composable(route = "orders") {
            OrderScreen()
        }
    }
}