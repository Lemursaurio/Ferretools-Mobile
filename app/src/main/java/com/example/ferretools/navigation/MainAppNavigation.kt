package com.example.ferretools.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
//import com.example.ferretools.navigation.RolUsuario
/*
enum class RolUsuario {
    NINGUNO, ADMIN, CLIENTE, ALMACENERO
}*/

@Composable
fun MainAppNavigation(navController: NavHostController) {
    //val navController = rememberNavController()

    // En una app real, esta variable se leería desde un ViewModel o una base de datos local
    var rolUsuario by remember { mutableStateOf(RolUsuario.NINGUNO) }

    NavHost(
        navController = navController,
        startDestination = "session"
    ) {
        // Flujo de sesión
        composable("session") {
            SessionNavGraph(
                navController = navController,
                onRolSeleccionado = { rol ->
                    rolUsuario = rol
                    when (rol) {
                        RolUsuario.ADMIN -> navController.navigate("admin") { popUpTo("session") { inclusive = true } }
                        RolUsuario.CLIENTE -> navController.navigate("cliente") { popUpTo("session") { inclusive = true } }
                        RolUsuario.ALMACENERO -> navController.navigate("almacenero") { popUpTo("session") { inclusive = true } }
                        else -> {}
                    }
                }
            )
        }

        // Flujo administrador
        composable("admin") {
            adminNavGraph(navController)
        }

        // Flujo cliente
        composable("cliente") {
            ClienteNavGraph(navController)
        }

        // Flujo almacenero
        composable("almacenero") {
            almaceneroNavGraph(navController)
        }
    }
}
