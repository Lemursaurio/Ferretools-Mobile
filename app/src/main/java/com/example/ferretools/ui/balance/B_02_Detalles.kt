package com.example.ferretools.ui.balance

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import com.example.ferretools.ui.components.TopNavBar

private val YellowPrimary = Color(0xFFFFEB3B)
private val GreenText = Color(0xFF388E3C)
private val RedText = Color.Red
private val CardBorder = Color.Black
private val CardBg = Color(0xFFEFEFEF)

data class DetalleBalance(
    val ingresos: Double,
    val egresos: Double,
    val balance: Double,
    val ventas: Int,
    val compras: Int,
    val efectivoIngresos: Double,
    val yapeIngresos: Double,
    val efectivoEgresos: Double,
    val yapeEgresos: Double,
    val gananciaProductos: List<Pair<String, Double>>
)

@Composable
fun B_02_Detalles(
    navController: NavController,
    // viewModel: DetallesBalanceViewModel = viewModel() // Para uso futuro
) {
    // Datos de ejemplo
    val detalle = DetalleBalance(
        ingresos = 2000.0,
        egresos = 800.0,
        balance = 1200.0,
        ventas = 15,
        compras = 7,
        efectivoIngresos = 1200.0,
        yapeIngresos = 800.0,
        efectivoEgresos = 500.0,
        yapeEgresos = 300.0,
        gananciaProductos = listOf("Arroz" to 300.0, "Azúcar" to 200.0, "Leche" to 100.0)
    )

    Scaffold(
        topBar = { TopNavBar(navController, "Detalles de balance") }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* TODO: Seleccionar fecha */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = YellowPrimary)
            ) {
                Text("Fecha", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Balance General
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, CardBorder, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Text("Balance General", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CardBg, RoundedCornerShape(8.dp))
                            .border(2.dp, CardBorder, RoundedCornerShape(8.dp))
                            .padding(16.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Ingresos", color = GreenText)
                                Text("S/ ${detalle.ingresos}", color = GreenText)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Egresos", color = RedText)
                                Text("S/ ${detalle.egresos}", color = RedText)
                            }
                            Divider(modifier = Modifier.padding(vertical = 8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Balance")
                                Text("S/ ${detalle.balance}")
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Resumen de ingresos
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = null, tint = GreenText, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Resumen de ingresos", fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("${detalle.ventas} ventas")
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Efectivo")
                        Text("S/ ${detalle.efectivoIngresos}")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Yape")
                        Text("S/ ${detalle.yapeIngresos}")
                    }

                    Divider(modifier = Modifier.padding(vertical = 12.dp))

                    // Resumen de egresos
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = RedText, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Resumen de egresos", fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("${detalle.compras} compras")
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Efectivo")
                        Text("S/ ${detalle.efectivoEgresos}")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Yape")
                        Text("S/ ${detalle.yapeEgresos}")
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Ganancia de productos
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardBg, RoundedCornerShape(8.dp))
                    .border(2.dp, CardBorder, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Text("Ganancia de Productos", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    detalle.gananciaProductos.forEach { (producto, ganancia) ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(producto)
                            Text("S/ $ganancia")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewB_02_Detalles() {
    val navController = rememberNavController()
    B_02_Detalles(navController = navController)
}