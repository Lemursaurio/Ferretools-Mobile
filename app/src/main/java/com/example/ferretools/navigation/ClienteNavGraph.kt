package com.example.ferretools.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

//import com.example.ferretools.ui.catalogo.I_C1_VerCatalogo
//import com.example.ferretools.ui.catalogo.I_C2_DetallesProducto
import com.example.ferretools.ui.configuracion.Config_01_Configuracion
import com.example.ferretools.ui.configuracion.Config_02_EditarPerfil
import com.example.ferretools.ui.configuracion.Config_05_CambiarContrasena
// import com.example.ferretools.ui.configuracion.Config_06_ConfirmarCerrarSesion // <-- aún falta

import com.example.ferretools.ui.pedido.P_01_AgregarAlCarrito
import com.example.ferretools.ui.pedido.P_02_CarritoCliente
//import com.example.ferretools.ui.pedido.P_03_ConfirmarPedido
import com.example.ferretools.ui.pedido.P_04_PedidoExitoso
import com.example.ferretools.ui.pedido.P_05_HistorialPedidos
import com.example.ferretools.ui.pedido.P_06_BoletaPedido

@Composable
fun ClienteNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "catalogo" // Puedes cambiar esto si deseas otra pantalla por defecto
    ) {/*
        // Catálogo
        composable("catalogo") { I_C1_VerCatalogo(navController) }
        composable("detalles_producto/{productoId}") { backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId") ?: ""
            I_C2_DetallesProducto(navController, productoId)
        }

        // Pedidos
        composable("agregar_al_carrito/{productoId}") { backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId") ?: ""
            P_01_AgregarAlCarrito(navController, productoId)
        }
        composable("carrito_cliente") { P_02_CarritoCliente(navController) }
        //composable("confirmar_pedido") { P_03_ConfirmarPedido(navController) }
        composable("pedido_exitoso") { P_04_PedidoExitoso(navController) }
        composable("historial_pedidos") { P_05_HistorialPedidos(navController) }
        composable("boleta_pedido/{pedidoId}") { backStackEntry ->
            val pedidoId = backStackEntry.arguments?.getString("pedidoId") ?: ""
            P_06_BoletaPedido(navController, pedidoId)
        }

        // Configuración
        composable("configuracion") { Config_01_Configuracion(navController) }
        composable("editar_perfil") { Config_02_EditarPerfil(navController) }
        composable("cambiar_contrasena") { Config_05_CambiarContrasena(navController) }
        // composable("confirmar_cerrar_sesion") { Config_06_ConfirmarCerrarSesion(navController) } // ← aún falta implementar
    */
    }
}