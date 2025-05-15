package com.example.ferretools.ui.pedido

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.navigation.AppRoutes

private val GreenPrimary = Color(0xFF22D366)
private val GreenSuccess = Color(0xFF00BF59)
private val RedError = Color.Red
private val BackgroundColor = Color(0xFFF8F8F8)
private val TextPrimary = Color(0xFF333333)
private val TextGray = Color.Gray

data class PedidoDetalle(
    val id: String,
    val cliente: String,
    val fecha: String,
    val productos: List<String>,
    val estado: String // "Pendiente", "Preparado", "Entregado", etc.
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun P_E2_DetallesPedido(
    navController: NavController,
    pedido: PedidoDetalle,
    onPrepararPedido: (() -> Unit)? = null,
    // viewModel: DetallesPedidoViewModel = viewModel() // Para uso futuro
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles del Pedido") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = GreenPrimary),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.LocalShipping, contentDescription = "Atrás", tint = Color.Black)
                    }
                }
            )
        },
        containerColor = BackgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        "Pedido #${pedido.id}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Cliente: ${pedido.cliente}", style = MaterialTheme.typography.bodyLarge)
                    Text("Fecha: ${pedido.fecha}", style = MaterialTheme.typography.bodyMedium, color = TextGray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Productos:", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    pedido.productos.forEach { producto ->
                        Text("- $producto", style = MaterialTheme.typography.bodyMedium)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = when (pedido.estado) {
                                "Preparado", "Entregado" -> GreenSuccess
                                "Cancelado" -> RedError
                                else -> TextGray
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Estado: ${pedido.estado}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = when (pedido.estado) {
                                "Preparado", "Entregado" -> GreenSuccess
                                "Cancelado" -> RedError
                                else -> TextGray
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Botón para preparar pedido solo si el estado es "Pendiente"
            if (pedido.estado == "Pendiente") {
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { onPrepararPedido?.invoke() },
                    colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Preparar Pedido", style = MaterialTheme.typography.titleMedium, color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewP_E2_DetallesPedido() {
    val navController = rememberNavController()
    val pedidoDemo = PedidoDetalle(
        id = "003",
        cliente = "Luis Torres",
        fecha = "2024-06-12",
        productos = listOf("Aceite x1", "Fideos x2", "Gaseosa x1"),
        estado = "Pendiente"
    )
    P_E2_DetallesPedido(
        navController = navController,
        pedido = pedidoDemo,
        onPrepararPedido = {}
    )
}