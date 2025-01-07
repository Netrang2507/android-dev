package com.example.apire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apire.presentation.Screen.Screen
import com.example.apire.presentation.coin_List.CoinListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.CoinListScreen.route
            ) {
                composable(
                    route = Screen.CoinListScreen.route
                ) {
                    CoinListScreen(navController)
                }
                composable(
                    route = Screen.CoinDetailScreen.route + "/{coinId}"
                ) {
                    Screen.CoinDetailScreen
                }
            }

        }
    }
}

