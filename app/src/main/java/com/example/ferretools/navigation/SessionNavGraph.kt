package com.example.ferretools.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.ui.session.*

@Composable
fun SessionNavGraph(
    navController: NavController,
    onRolSeleccionado: (RolUsuario) -> Unit
) {
    val sessionNavController = rememberNavController()

    NavHost(
        navController = sessionNavController,
        startDestination = AppRoutes.Auth.WELCOME
    ) {
        composable(AppRoutes.Auth.WELCOME) {
            S_01_PortadaBienvenida(navController = navController)
        }
        composable(AppRoutes.Auth.SELECT_ROLE) {
            S_02_SeleccionRol(navController = navController)
        }
//        composable(AppRoutes.Auth.REGISTER_USER) {
//            S_03_RegistroUsuario(navController = navController)
//        }
        composable(AppRoutes.Auth.REGISTER_BUSINESS) {
            S_04_RegistroNegocio(navController = navController)
        }
        composable(AppRoutes.Auth.LOGIN) {
            S_05_IniciarSesion(navController = navController)
        }
        composable(AppRoutes.Auth.RECOVER_PASSWORD) {
            S_06_RecuperarContrasena(navController = navController)
        }
        composable(AppRoutes.Auth.CHANGE_PASSWORD) {
            S_07_CambiarContrasena(navController = navController)
        }
    }
}
