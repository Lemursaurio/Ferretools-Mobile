package com.example.ferretools.ui.cliente

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ClienteHistorialTopBar(navController: NavController, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF22D366))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Atrás",
            tint = Color.Black,
            modifier = Modifier
                .size(26.dp)
                .clickable { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

@Composable
fun PedidoClienteHistorialCard(pedido: PedidoCliente, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.White,
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }
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

@Composable
fun ListaHistorialPedidosCliente(
    pedidos: List<PedidoCliente>,
    onPedidoClick: (PedidoCliente) -> Unit
) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        Text(
            "Historial de Pedidos",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.padding(vertical = 12.dp)
        )
        if (pedidos.isEmpty()) {
            Text("No hay pedidos en el historial.", color = Color.Gray, modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn {
                items(pedidos) { pedido ->
                    PedidoClienteHistorialCard(pedido) { onPedidoClick(pedido) }
                }
            }
        }
    }
}

@Composable
fun ClienteHistorialPedidosScreen(
    navController: NavController,
    pedidosHistorial: List<PedidoCliente>,
    onPedidoClick: (PedidoCliente) -> Unit
) {
    Scaffold(
        topBar = { ClienteHistorialTopBar(navController, text = "Historial de Pedidos") },
        backgroundColor = Color(0xFFF8F8F8)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            ListaHistorialPedidosCliente(
                pedidos = pedidosHistorial,
                onPedidoClick = onPedidoClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClienteHistorialPedidosScreenPreview() {
    val navController = rememberNavController()
    val pedidosDemo = listOf(
        PedidoCliente(
            id = "1001",
            fecha = "2024-06-10",
            estado = "Entregado",
            total = "S/ 45.00"
        ),
        PedidoCliente(
            id = "1002",
            fecha = "2024-06-12",
            estado = "Listo",
            total = "S/ 30.00"
        ),
        PedidoCliente(
            id = "1003",
            fecha = "2024-06-13",
            estado = "En preparación",
            total = "S/ 20.00"
        )
    )
    ClienteHistorialPedidosScreen(
        navController = navController,
        pedidosHistorial = pedidosDemo,
        onPedidoClick = {}
    )
}