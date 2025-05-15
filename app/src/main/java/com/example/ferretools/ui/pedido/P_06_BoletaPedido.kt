package com.example.ferretools.ui.pedido

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.ui.components.TopNavBar
import com.example.ferretools.ui.components.boleta.BoletaNavBar
import com.example.ferretools.ui.components.boleta.DetalleProductoFila

// --- Data Class para la Boleta ---
data class BoletaPedido(
    val fecha: String,
    val medioPago: String,
    val productos: List<ProductoBoleta>,
    val total: String
)

data class ProductoBoleta(
    val nombre: String,
    val cantidad: String,
    val precio: String
)

@Composable
fun P_06_BoletaPedido(
    navController: NavController,
    // viewModel: BoletaPedidoViewModel = viewModel() // Para uso futuro
) {
    // Datos de ejemplo - En el futuro vendrán del ViewModel
    val boleta = BoletaPedido(
        fecha = "10/06/2024",
        medioPago = "Efectivo",
        productos = listOf(
            ProductoBoleta("Producto 01", "1", "S/ 15.00"),
            ProductoBoleta("Producto 02", "2", "S/ 30.00"),
            ProductoBoleta("Producto 03", "15", "S/ 225.00")
        ),
        total = "S/ 270.00"
    )

    Scaffold(
        topBar = { TopNavBar(navController, "Boleta de pedido") }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Caja principal con detalles
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(400.dp)
                    .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Encabezados
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                "Fecha de pedido",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                "Medio de pago",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Column {
                            Text(
                                boleta.fecha,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                boleta.medioPago,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Productos:",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Tabla de productos
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Nombre",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Cantidad",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Precio",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Divider()
                    // Productos
                    boleta.productos.forEach { producto ->
                        DetalleProductoFila(
                            nombre = producto.nombre,
                            cantidad = producto.cantidad,
                            precio = producto.precio
                        )
                    }
                    Divider(modifier = Modifier.padding(vertical = 4.dp))
                    // Total
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "TOTAL",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            boleta.total,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Acciones
            BoletaNavBar(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewP_06_BoletaPedido() {
    val navController = rememberNavController()
    P_06_BoletaPedido(navController = navController)
}