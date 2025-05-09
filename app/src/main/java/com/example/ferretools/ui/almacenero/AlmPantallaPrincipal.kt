package com.example.ferretools.ui.almacenero

import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text

//import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.draw.clip
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.ui.cliente.ClienteQuickAccess
import com.example.ferretools.ui.cliente.ClienteQuickAccessButton

data class PedidoPendiente(
    val id: String,
    val cliente: String,
    val fecha: String,
    val productos: List<String>,
    val estado: String // Ejemplo: "Pendiente", "En preparación"
)

@Composable
fun PedidoPendienteCard(pedido: PedidoPendiente, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Pedido #${pedido.id}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(Modifier.weight(1f))
                Text(pedido.estado, color = Color(0xFFE65100), fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
            Spacer(Modifier.height(4.dp))
            Text("Cliente: ${pedido.cliente}", fontSize = 14.sp)
            Text("Fecha: ${pedido.fecha}", fontSize = 13.sp, color = Color.Gray)
            Spacer(Modifier.height(4.dp))
            Text("Productos:", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
            pedido.productos.forEach {
                Text("- $it", fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun ListaPedidosPendientes(
    pedidos: List<PedidoPendiente>,
    onPedidoClick: (PedidoPendiente) -> Unit
) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        Text(
            "Pedidos Pendientes",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.padding(vertical = 12.dp)
        )
        if (pedidos.isEmpty()) {
            Text("No hay pedidos pendientes.", color = Color.Gray, modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn {
                items(pedidos) { pedido ->
                    PedidoPendienteCard(pedido) { onPedidoClick(pedido) }
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(containerColor = Color(0xFF22D366)) {
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.Person, contentDescription = "Inicio") },
            label = { Text("Inicio") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("inventario") },
            icon = { Icon(Icons.Default.List, contentDescription = "Inventario") },
            label = { Text("Inventario") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.History, contentDescription = "Balance") },
            label = { Text("Historial") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.Person, contentDescription = "Cuenta") },
            label = { Text("Cuenta") }
        )
    }
}

@Composable
fun TopNavBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF22D366))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Imagen de usuario (placeholder)
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        ) {
            Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.align(Alignment.Center))
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("Nombre de Usuario", color = Color.White, fontWeight = FontWeight.Bold)
            Text("Nombre de la Tienda", color = Color.White, fontSize = 13.sp)
        }
    }
}

@Composable
fun AlmaceneroQuickAccess(
    onCatalogo: () -> Unit,
    onCarrito: () -> Unit,
    onHistorial: () -> Unit
) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        Text(
            "Accesos Rápidos",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        Spacer(Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            ClienteQuickAccessButton("Catálogo", Icons.Default.List, onCatalogo)
            ClienteQuickAccessButton("Carrito", Icons.Default.ShoppingCart, onCarrito)
            ClienteQuickAccessButton("Historial", Icons.Default.History, onHistorial)
        }
    }
}

@Composable
fun ClienteQuickAccessButton(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color(0xFF22D366), shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Icon(icon, contentDescription = label, tint = Color.Black, modifier = Modifier.size(32.dp))
        Text(label, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Composable
fun AlmaceneroDashboardScreen(
    navController: NavController,
    pedidosPendientes: List<PedidoPendiente>,
    onCatalogo: () -> Unit,
    onCarrito: () -> Unit,
    onHistorial: () -> Unit,
    onPedidoClick: (PedidoPendiente) -> Unit
) {
    Scaffold(
        topBar = { TopNavBar(navController) },
        bottomBar = { BottomNavBar(navController) },
        containerColor = Color(0xFFF8F8F8)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Spacer(Modifier.height(8.dp))
            ClienteQuickAccess(
                onCatalogo = onCatalogo,
                onCarrito = onCarrito,
                onHistorial = onHistorial
            )
            Spacer(Modifier.height(8.dp))

            ListaPedidosPendientes(
                pedidos = pedidosPendientes,
                onPedidoClick = onPedidoClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlmaceneroDashboardScreenPreview() {
    val navController = rememberNavController()
    val pedidosDemo = listOf(
        PedidoPendiente(
            id = "001",
            cliente = "Juan Pérez",
            fecha = "2024-06-10",
            productos = listOf("Arroz x2", "Azúcar x1"),
            estado = "Pendiente"
        ),
        PedidoPendiente(
            id = "002",
            cliente = "María López",
            fecha = "2024-06-11",
            productos = listOf("Leche x3", "Pan x5"),
            estado = "Pendiente"
        )
    )
    AlmaceneroDashboardScreen(
        navController = navController,
        pedidosPendientes = pedidosDemo,
        onCatalogo = {},
        onCarrito = {},
        onHistorial = {},
        onPedidoClick = {}
    )
}

