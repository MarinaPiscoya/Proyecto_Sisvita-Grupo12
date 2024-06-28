package com.example.sysvita.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sysvita.screen.HomeScreen
import com.example.sysvita.screen.LoginScreen
import com.example.sysvita.screen.RegisterScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(Screen.Home.route + "/{name}/{direccion}/{email}") { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString("name")
            val direccion = navBackStackEntry.arguments?.getString("direccion")
            val email = navBackStackEntry.arguments?.getString("email")

            HomeScreen(navController, name, direccion, email)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }
    }
}





