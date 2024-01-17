package com.dastanapps.marketstrategy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.dastanapps.marketstrategy.ui.navigation.Navigation
import com.dastanapps.marketstrategy.ui.theme.MarketStrategyTheme
import com.dastanapps.marketstrategy.utils.toast
import com.dastanapps.marketstrategy.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            SystemBarStyle.auto(
//                MaterialTheme.colorScheme.background.toArgb(),
//                MaterialTheme.colorScheme.background.toArgb()
//            )
            val navController = rememberNavController()
            MarketStrategyTheme {
                Navigation(navController, Modifier)
            }
        }

        mainViewModel.toastCallback = {
            toast("Order Executed")
        }
    }
}