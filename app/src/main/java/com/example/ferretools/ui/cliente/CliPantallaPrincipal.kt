package com.example.ferretools.ui.cliente

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.History
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class PedidoCliente(
    val id: String,
    val fecha: String,
    val estado: String, // "En preparación", "Listo", "Entregado"
    val total: String
)

@Composable
fun ClienteHeader(userName: String, storeName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF22D366))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
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
            Text(userName, color = Color.White, fontWeight = FontWeight.Bold)
            Text(storeName, color = Color.White, fontSize = 13.sp)
        }
    }
}

@Composable
fun ClienteQuickAccess(
    onCatalogo: () -> Unit,
    onCarrito: () -> Unit,
    onHistorial: () -> Unit
) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        Text("Accesos Rápidos", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF333333))
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
fun PedidosRecientesCliente(pedidos: List<PedidoCliente>, onPedidoClick: (PedidoCliente) -> Unit) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        Text(
            "Pedidos Pendientes",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.padding(vertical = 12.dp)
        )
        if (pedidos.isEmpty()) {
            Text("Aún no has realizado pedidos.", color = Color.Gray, modifier = Modifier.padding(16.dp))
        } else {
            pedidos.take(3).forEach { pedido ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    backgroundColor = Color.White,
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { onPedidoClick(pedido) }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(Modifier.weight(1f)) {
                            Text("Pedido #${pedido.id}", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Text("Fecha: ${pedido.fecha}", fontSize = 13.sp, color = Color.Gray)
                            Text("Total: ${pedido.total}", fontSize = 13.sp)
                        }
                        Text(
                            pedido.estado,
                            color = when (pedido.estado) {
                                "Entregado" -> Color(0xFF22D366)
                                "Listo" -> Color(0xFF00BF59)
                                else -> Color(0xFFE65100)
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ClienteBottomNavBar(selected: Int, onSelect: (Int) -> Unit) {
    BottomNavigation(
        backgroundColor = Color(0xFF22D366)
    ) {
        val items = listOf("Inicio", "Catálogo", "Historial", "Cuenta")
        val icons = listOf(Icons.Default.Home, Icons.Default.List, Icons.Default.History, Icons.Default.Person)
        items.forEachIndexed { idx, label ->
            BottomNavigationItem(
                icon = { Icon(icons[idx], contentDescription = label) },
                label = { Text(label) },
                selected = selected == idx,
                onClick = { onSelect(idx) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color(0xFF666666)
            )
        }
    }
}

@Composable
fun ClienteHomeScreen(
    userName: String,
    storeName: String,
    pedidosRecientes: List<PedidoCliente>,
    selectedMenu: Int,
    onMenuSelect: (Int) -> Unit,
    onCatalogo: () -> Unit,
    onCarrito: () -> Unit,
    onHistorial: () -> Unit,
    onPedidoClick: (PedidoCliente) -> Unit
) {
    Scaffold(
        topBar = { ClienteHeader(userName, storeName) },
        bottomBar = { ClienteBottomNavBar(selected = selectedMenu, onSelect = onMenuSelect) },
        backgroundColor = Color(0xFFF8F8F8)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Spacer(Modifier.height(12.dp))
            Text(
                "¡Bienvenido, $userName!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(8.dp))
            ClienteQuickAccess(
                onCatalogo = onCatalogo,
                onCarrito = onCarrito,
                onHistorial = onHistorial
            )
            Spacer(Modifier.height(16.dp))
            PedidosRecientesCliente(
                pedidos = pedidosRecientes,
                onPedidoClick = onPedidoClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClienteHomeScreenPreview() {
    val pedidosDemo = listOf(
        PedidoCliente(
            id = "1001",
            fecha = "2024-06-10",
            estado = "Pendiente",
            total = "S/ 45.00"
        ),
        PedidoCliente(
            id = "1002",
            fecha = "2024-06-12",
            estado = "Pendiente",
            total = "S/ 30.00"
        )
    )
    ClienteHomeScreen(
        userName = "Carlos Ruiz",
        storeName = "Bodega Central",
        pedidosRecientes = pedidosDemo,
        selectedMenu = 0,
        onMenuSelect = {},
        onCatalogo = {},
        onCarrito = {},
        onHistorial = {},
        onPedidoClick = {}
    )
}