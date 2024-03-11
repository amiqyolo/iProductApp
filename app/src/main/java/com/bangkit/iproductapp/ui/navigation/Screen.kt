package com.bangkit.iproductapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("cart")
    object Detail : Screen("home/{id}") {
        fun createRoute(id: Int) = "home/$id"
    }
}
