package com.example.myapplication

import androidx.navigation.NavArgs

sealed class Screen(val route: String) {
    object MainScreen : Screen("mainScreen")
    object DetailScreen : Screen("detail_screen")

    fun withArgs(vararg  args: String):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}