package com.example.ferretools.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.ui.session.*
import com.example.ferretools.ui.configuracion.*
import com.example.ferretools.ui.home.*
import com.example.ferretools.ui.inventario.*
import com.example.ferretools.ui.compra.*
import com.example.ferretools.ui.venta.*
import com.example.ferretools.ui.pedido.*
import com.example.ferretools.ui.balance.*

@Composable
fun AppNavigation(navController: NavHostController) {
    //val navController = rememberNavController()

    // --- MOCKS Y DATOS DE EJEMPLO ---
    val mockPedidosHistorial = listOf(
        PedidoHistorial(
            id = "001",
            cliente = "Juan Pérez",
            fecha = "2024-06-10",
            productos = listOf("Arroz x2", "Azúcar x1"),
            estado = "Entregado"
        )
    )
    val mockPedidoDetalle = PedidoDetalle(
        id = "001",
        cliente = "Juan Pérez",
        fecha = "2024-06-10",
        productos = listOf("Arroz x2", "Azúcar x1"),
        estado = "Pendiente"
    )
    val pedidosDemo = listOf(
        PedidoCliente("1001", "2024-06-10", "Entregado", "S/ 45.00"),
        PedidoCliente("1002", "2024-06-12", "Listo", "S/ 30.00"),
        PedidoCliente("1003", "2024-06-13", "En preparación", "S/ 20.00")
    )

    NavHost(
        navController = navController,
        startDestination = AppRoutes.Auth.WELCOME
    ) {
        // Auth Stack
        composable(AppRoutes.Auth.WELCOME) {
            S_01_PortadaBienvenida(navController = navController)
        }
        composable(AppRoutes.Auth.SELECT_ROLE) {
            S_02_SeleccionRol(navController = navController)
        }
        composable(AppRoutes.Auth.REGISTER_USER) {
            S_03_RegistroUsuario(navController = navController)
        }
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

        // Configuración Stack
        composable(AppRoutes.Config.MAIN) {
            Config_01_Configuracion(
                navController = navController,
                darkModeEnabled = false,
                stockNotificationEnabled = false,
                userEmail = "demo@email.com",
                userLastName = "Demo",
                userName = "Usuario Demo",
                userPhone = "999999999"
            )
        }
        composable(AppRoutes.Config.EDIT_PROFILE) {
            Config_02_EditarPerfil(
                navController = navController,
                initialEmail = "demo@email.com",
                initialLastName = "Demo",
                initialName = "Usuario",
                initialPhone = "999999999"
            )
        }
        composable(AppRoutes.Config.EDIT_BUSINESS) {
            Config_03_EditarNegocio(
                navController = navController,
                initialAddress = "Calle Falsa 123",
                initialBusinessName = "Negocio Demo",
                initialBusinessType = "Bodega",
                initialRuc = "12345678901"
            )
        }
        composable(AppRoutes.Config.CHANGE_QR) {
            Config_04_CambiarQRYape(navController = navController)
        }
        composable(AppRoutes.Config.CHANGE_PASSWORD) {
            Config_05_CambiarContrasena(navController = navController)
        }
        // Dashboard Stack
        composable(AppRoutes.Admin.DASHBOARD) {
            HOME_Admin(navController = navController)
        }
        composable(AppRoutes.Client.DASHBOARD) {
            HOME_Cliente(navController = navController)
        }
        composable(AppRoutes.Employee.DASHBOARD) {
            HOME_Empleado(navController = navController)
        }
        // Inventario Stack
        composable(AppRoutes.Inventory.LIST_PRODUCTS) {
            I_01_ListaProductos(navController = navController)
        }
        composable(AppRoutes.Inventory.ADD_PRODUCT) {
            I_02_AgregarProducto(navController = navController)
        }
        composable(AppRoutes.Inventory.PRODUCT_REPORT) {
            I_05_ReporteProducto(navController = navController)
        }
        composable(AppRoutes.Inventory.LIST_CATEGORIES) {
            I_08_ListaCategorias(navController = navController)
        }
        composable(AppRoutes.Inventory.ADD_CATEGORY) {
            I_09_CrearCategoria(navController = navController)
        }
        composable(AppRoutes.Inventory.CATEGORY_DETAILS) {
            I_10_DetallesCategoria(navController = navController)
        }
        composable(AppRoutes.Inventory.INVENTORY_REPORT) {
            I_12_ReporteInventario(navController = navController)
        }
        // Compras Stack
        composable(AppRoutes.Purchase.CART) {
            C_01_CarritoCompra(navController = navController)
        }
        composable(AppRoutes.Purchase.CART_SUMMARY) {
            C_02_ResumenCarritoCompra(navController = navController)
        }
        composable(AppRoutes.Purchase.SUCCESS) {
            C_04_CompraExitosa(navController = navController)
        }
        composable(AppRoutes.Purchase.RECEIPT) {
            C_05_BoletaCompra(navController = navController)
        }
        // Ventas Stack
        composable(AppRoutes.Sale.CART) {
            V_01_CarritoVenta(navController = navController)
        }
        composable(AppRoutes.Sale.CART_SUMMARY) {
            V_02_ResumenCarritoVenta(navController = navController)
        }
        composable(AppRoutes.Sale.SUCCESS) {
            V_04_VentaExitosa(navController = navController)
        }
        composable(AppRoutes.Sale.RECEIPT) {
            V_05_BoletaVenta(navController = navController)
        }
        // Pedidos Stack
        composable(AppRoutes.Order.ADD_TO_CART) {
            P_01_AgregarAlCarrito(navController = navController)
        }
        composable(AppRoutes.Order.CART) {
            P_02_CarritoCliente(navController = navController)
        }
        composable(AppRoutes.Order.CONFIRM) {
            P_03_ConfirmarPedido(navController = navController)
        }
        composable(AppRoutes.Order.SUCCESS) {
            P_04_PedidoExitoso(navController = navController)
        }
        composable(AppRoutes.Order.HISTORY) {
            P_05_HistorialPedidos(
                navController = navController,
                pedidosHistorial = pedidosDemo,
                selectedMenu = 2,
                onMenuSelect = {},
                onPedidoClick = {},
                userName = "Cliente Demo",
                storeName = "Tienda Demo"
            )
        }
        composable(AppRoutes.Order.RECEIPT) {
            P_06_BoletaPedido(navController = navController)
        }
        composable(AppRoutes.Order.Employee.HISTORY) {
            P_E1_HistorialPedidos(
                navController = navController,
                pedidosHistorial = mockPedidosHistorial,
                onPedidoClick = {},
                userName = "Empleado Demo",
                storeName = "Tienda Demo"
            )
        }
        composable(AppRoutes.Order.Employee.DETAILS) {
            P_E2_DetallesPedido(
                navController = navController,
                pedido = mockPedidoDetalle,
                onPrepararPedido = {}
            )
        }
        composable(AppRoutes.Order.Employee.PREPARE) {
            P_E3_PrepararPedido(
                navController = navController,
                pedidoId = "001",
                onPedidoPreparado = {}
            )
        }
        // Balance Stack
        composable(AppRoutes.Balance.LIST) {
            B_01_Balances(navController = navController)
        }
        composable(AppRoutes.Balance.DETAILS) {
            B_02_Detalles(navController = navController)
        }
        composable(AppRoutes.Balance.REPORT) {
            B_03_Reporte(navController = navController)
        }
    }
}