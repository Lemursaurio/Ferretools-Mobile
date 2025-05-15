package com.example.ferretools.ui.pedido

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.navigation.AppRoutes
import com.example.ferretools.ui.home.EmpleadoBottomNavBar

// --- Constantes de Estilo ---
private val GreenPrimary = Color(0xFF22D366)
private val GreenSuccess = Color(0xFF00BF59)
private val RedError = Color.Red
private val BackgroundColor = Color(0xFFF8F8F8)
private val TextPrimary = Color(0xFF333333)
private val TextGray = Color.Gray
private val CardElevation = 2.dp

// --- DATA CLASS ---
data class PedidoHistorial(
    val id: String,
    val cliente: String,
    val fecha: String,
    val productos: List<String>,
    val estado: String // Ej: "Entregado", "Cancelado"
)

// --- COMPONENTES UI ---
@Composable
fun AlmaceneroTopBar(
    navController: NavController,
    userName: String = "Nombre de Usuario",
    storeName: String = "Nombre de la Tienda",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(GreenPrimary)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                userName,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                storeName,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

@Composable
fun PedidoHistorialCard(
    pedido: PedidoHistorial,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = CardElevation),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Pedido #${pedido.id}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = pedido.estado,
                    color = when (pedido.estado) {
                        "Entregado" -> GreenSuccess
                        "Cancelado" -> RedError
                        else -> TextGray
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Cliente: ${pedido.cliente}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                "Fecha: ${pedido.fecha}",
                style = MaterialTheme.typography.bodyMedium,
                color = TextGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Productos:",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            pedido.productos.forEach { producto ->
                Text(
                    "- $producto",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun ListaHistorialPedidos(
    pedidos: List<PedidoHistorial>,
    onPedidoClick: (PedidoHistorial) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Historial de Pedidos",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.padding(vertical = 12.dp)
        )
        if (pedidos.isEmpty()) {
            Text(
                text = "No hay pedidos en el historial.",
                style = MaterialTheme.typography.bodyLarge,
                color = TextGray,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn {
                items(pedidos) { pedido ->
                    PedidoHistorialCard(pedido = pedido) { onPedidoClick(pedido) }
                }
            }
        }
    }
}

// --- PANTALLA PRINCIPAL ---
@Composable
fun P_E1_HistorialPedidos(
    navController: NavController,
    pedidosHistorial: List<PedidoHistorial>,
    onPedidoClick: (PedidoHistorial) -> Unit,
    userName: String = "Nombre de Usuario",
    storeName: String = "Nombre de la Tienda"
    // viewModel: HistorialPedidosEmpleadoViewModel = viewModel() // Para uso futuro
) {
    Scaffold(
        topBar = { AlmaceneroTopBar(navController, userName, storeName) },
        bottomBar = { EmpleadoBottomNavBar(
            onInicio = { /* Pantalla actual */ },
            onInventario = { navController.navigate(AppRoutes.Inventory.LIST_PRODUCTS) },
            onHistorial = { navController.navigate(AppRoutes.Order.Employee.HISTORY) },
            onCuenta = { navController.navigate(AppRoutes.Config.MAIN) }) },
        containerColor = BackgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ListaHistorialPedidos(
                pedidos = pedidosHistorial,
                onPedidoClick = { pedido ->
                    onPedidoClick(pedido)
                    navController.navigate(AppRoutes.Order.Employee.DETAILS)
                }
            )
        }
    }
}

// --- PREVIEW ---
@Preview(showBackground = true)
@Composable
fun PreviewP_E1_HistorialPedidos() {
    val navController = rememberNavController()
    val pedidosDemo = listOf(
        PedidoHistorial(
            id = "001",
            cliente = "Juan Pérez",
            fecha = "2024-06-10",
            productos = listOf("Arroz x2", "Azúcar x1"),
            estado = "Entregado"
        ),
        PedidoHistorial(
            id = "002",
            cliente = "María López",
            fecha = "2024-06-11",
            productos = listOf("Leche x3", "Pan x5"),
            estado = "Cancelado"
        )
    )
    P_E1_HistorialPedidos(
        navController = navController,
        pedidosHistorial = pedidosDemo,
        onPedidoClick = {},
        userName = "Carlos Ruiz",
        storeName = "Bodega Central"
    )
}