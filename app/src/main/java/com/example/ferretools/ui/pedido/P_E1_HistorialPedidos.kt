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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.ui.home.BottomNavBar

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
fun AlmaceneroTopBar(navController: NavController, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF22D366))
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
            Text("Nombre de Usuario", color = Color.White, fontWeight = FontWeight.Bold)
            Text("Nombre de la Tienda", color = Color.White, fontSize = 13.sp)
        }
    }
}

@Composable
fun PedidoHistorialCard(pedido: PedidoHistorial, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Pedido #${pedido.id}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = pedido.estado,
                    color = if (pedido.estado == "Entregado") Color(0xFF00BF59) else Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text("Cliente: ${pedido.cliente}", fontSize = 14.sp)
            Text("Fecha: ${pedido.fecha}", fontSize = 13.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Productos:", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
            pedido.productos.forEach { producto ->
                Text("- $producto", fontSize = 13.sp)
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
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.padding(vertical = 12.dp)
        )
        if (pedidos.isEmpty()) {
            Text(
                text = "No hay pedidos en el historial.",
                color = Color.Gray,
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
fun HistorialPedidosScreen(
    navController: NavController,
    pedidosHistorial: List<PedidoHistorial>,
    onPedidoClick: (PedidoHistorial) -> Unit
) {
    Scaffold(
        topBar = { AlmaceneroTopBar(navController) },
        bottomBar = { BottomNavBar(navController) },
        containerColor = Color(0xFFF8F8F8)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ListaHistorialPedidos(pedidos = pedidosHistorial, onPedidoClick = onPedidoClick)
        }
    }
}

// --- PREVIEW ---
@Preview(showBackground = true)
@Composable
fun PreviewHistorialPedidosScreen() {
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
    HistorialPedidosScreen(
        navController = navController,
        pedidosHistorial = pedidosDemo,
        onPedidoClick = {}
    )
}