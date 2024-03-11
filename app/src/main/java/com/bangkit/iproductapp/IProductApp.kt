@file:OptIn(ExperimentalMaterial3Api::class)

package com.bangkit.iproductapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bangkit.iproductapp.ui.component.AppBar
import com.bangkit.iproductapp.ui.navigation.Screen
import com.bangkit.iproductapp.ui.screen.detail.DetailScreen
import com.bangkit.iproductapp.ui.screen.home.HomeScreen
import com.bangkit.iproductapp.ui.screen.profile.ProfileScreen
import com.bangkit.iproductapp.ui.theme.IProductAppTheme

@Composable
fun ProductApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == Screen.Home.route) {
                AppBar(
                    navController = navController,
                    modifier = modifier.fillMaxWidth()
                )
            }
        },
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = modifier.padding(innerPadding)
            ) {
                composable(route = Screen.Home.route) {
                    HomeScreen(
                        navigateToDetail = { id ->
                            navController.navigate(Screen.Detail.createRoute(id))
                        }
                    )
                }
                composable(route = Screen.Profile.route) {
                    ProfileScreen(
                        onNavigateUp = {
                            navController.navigateUp()
                        }
                    )
                }
                composable(
                    route = Screen.Detail.route,
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
                ) {
                    val id = it.arguments?.getInt("id") ?: -1
                    DetailScreen(
                        id = id,
                        navigateBack = { navController.navigateUp() }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProductAppPreview() {
    IProductAppTheme {
        ProductApp()
    }
}