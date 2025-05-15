package com.example.ferretools.ui.compra

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.ui.components.TopNavBar
import com.example.ferretools.ui.components.boleta.BoletaNavBar
import com.example.ferretools.ui.components.boleta.DetalleProductoFila

@Composable
fun BoletaCompraScreen(navController: NavController) {
    Scaffold(
        topBar = { TopNavBar(navController, "Boleta de compra") }
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
                            Text("Fecha de compra", fontSize = 14.sp)
                            Text("Medio de pago", fontSize = 14.sp)
                        }
                        Column {
                            Text("Fecha de compra", fontSize = 14.sp)
                            Text("Medio de pago", fontSize = 14.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Productos:", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    // Tabla de productos
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Nombre", fontWeight = FontWeight.Bold)
                        Text("Cantidad", fontWeight = FontWeight.Bold)
                        Text("Precio", fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Divider()
                    // Productos
                    DetalleProductoFila("Producto 01", "1", "$ XX.XX")
                    DetalleProductoFila("Producto 02", "2", "$ XX.XX")
                    DetalleProductoFila("Producto 03", "15", "$ XX.XX")
                    Divider(modifier = Modifier.padding(vertical = 4.dp))
                    // Total
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("TOTAL", fontWeight = FontWeight.Bold)
                        Text("$ XX.XX", fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Acciones
            BoletaNavBar()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBoletaCompraScreen() {
    val navController = rememberNavController()
    BoletaCompraScreen(navController = navController)
}