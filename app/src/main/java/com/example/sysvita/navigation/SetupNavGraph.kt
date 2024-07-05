package com.example.sysvita.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.sysvita.screen.CuestionarioCompletoScreen
import com.example.sysvita.screen.CuestionariosScreen
import com.example.sysvita.screen.DiagnosticoScreen
import com.example.sysvita.screen.GestionPacientesScreen
import com.example.sysvita.screen.HomeScreen
import com.example.sysvita.screen.LoginScreen
import com.example.sysvita.screen.RegisterEspecialistaScreen
import com.example.sysvita.screen.RegisterScreen
import com.example.sysvita.screen.ResultadosScreen
import com.example.sysvita.screen.SeleccionTipoRegistroScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(Screen.Home.route + "/{nom_completo}/{edad}/{num_cel}") { navBackStackEntry ->
            val nom_completo = navBackStackEntry.arguments?.getString("nom_completo")
            val edad = navBackStackEntry.arguments?.getString("edad")?.toIntOrNull()
            val num_cel = navBackStackEntry.arguments?.getString("num_cel")?.toIntOrNull()

            HomeScreen(navController, nom_completo, edad, num_cel)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(
            route = Screen.Register.route,
            arguments = listOf(navArgument("idTipoUsuario") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val idTipoUsuario = navBackStackEntry.arguments?.getInt("idTipoUsuario") ?: 0
            RegisterScreen(navController, idTipoUsuario)
        }

        composable(Screen.RegisterEspecialista.route) {
            RegisterEspecialistaScreen(navController)
        }


        composable(Screen.SeleccionTipoRegistro.route) {
            SeleccionTipoRegistroScreen(navController)
        }

        composable(Screen.Cuestionarios.route) {
            CuestionariosScreen(navController = navController)
        }

        composable(
            route = "${Screen.CuestionarioCompleto.route}/{cuestionarioId}",
            arguments = listOf(navArgument("cuestionarioId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val cuestionarioId = navBackStackEntry.arguments?.getInt("cuestionarioId") ?: 0
            CuestionarioCompletoScreen(navController = navController, cuestionarioId = cuestionarioId)
        }

        composable(
            route = "${Screen.Resultados.route}/{cuestionarioId}/{totalPuntuacion}",
            arguments = listOf(
                navArgument("cuestionarioId") { type = NavType.IntType },
                navArgument("totalPuntuacion") { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            val cuestionarioId = navBackStackEntry.arguments?.getInt("cuestionarioId") ?: 0
            val totalPuntuacion = navBackStackEntry.arguments?.getInt("totalPuntuacion") ?: 0
            ResultadosScreen(navController = navController, cuestionarioId = cuestionarioId, totalPuntuacion = totalPuntuacion)
        }

        composable(Screen.GestionPacientes.route) {
            GestionPacientesScreen(navController = navController)
        }

        composable(Screen.Diagnostico.route + "/{idCuestDet}/{nomCompleto}/{cuestionario}/{puntTotal}/{nivel}",
            arguments = listOf(
                navArgument("idCuestDet") { type = NavType.IntType },
                navArgument("nomCompleto") { type = NavType.StringType },
                navArgument("cuestionario") { type = NavType.StringType },
                navArgument("puntTotal") { type = NavType.IntType },
                navArgument("nivel") { type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            val idCuestDet = navBackStackEntry.arguments?.getInt("idCuestDet")
            val nomCompleto = navBackStackEntry.arguments?.getString("nomCompleto")
            val cuestionario = navBackStackEntry.arguments?.getString("cuestionario")
            val puntTotal = navBackStackEntry.arguments?.getInt("puntTotal")
            val nivel = navBackStackEntry.arguments?.getString("nivel")

            DiagnosticoScreen(navController, idCuestDet, nomCompleto, cuestionario, puntTotal, nivel)
        }
    }
}
