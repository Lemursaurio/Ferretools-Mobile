package com.example.ferretools.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ferretools.ui.home.HomeScreen
import com.example.ferretools.ui.inventario.InventarioScreen
import com.example.ferretools.ui.inventario.ReporteScreen
import com.example.ferretools.ui.inventario.CategoriasScreen
import com.example.ferretools.ui.inventario.ProductosCategoriaScreen
import com.example.ferretools.ui.producto.AgregarProductoScreen
import com.example.ferretools.ui.producto.CrearCategoriaScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("inventario") {
            InventarioScreen(navController)
        }
        composable("reporte") {
            ReporteScreen()
        }
        composable("categorias") {
            CategoriasScreen(navController)
        }
        composable("productos_categoria/{categoria}") { backStackEntry ->
            ProductosCategoriaScreen(
                navController = navController,
                categoria = backStackEntry.arguments?.getString("categoria") ?: ""
            )
        }
        composable("agregar_producto") {
            AgregarProductoScreen(navController)
        }
        composable("crear_categoria") {
            CrearCategoriaScreen(navController)
        }
    }
} 