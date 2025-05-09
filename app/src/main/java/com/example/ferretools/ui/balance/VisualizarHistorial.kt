package com.example.ferretools.ui.balance

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.R
import com.example.ferretools.ui.components.BottomNavBar
import com.example.ferretools.ui.components.SelectorOpciones
import com.example.ferretools.ui.components.UserDataBar
import com.example.ferretools.ui.components.detalles_cv.CampoFechaSeleccion

@Composable
fun VisualizarHistorialScreen(navController: NavController) {
    Scaffold(
        topBar = { UserDataBar("Nombre de usuario","Nombre de tienda") },
        bottomBar = { BottomNavBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Fecha
            Text("Fecha")

            // Fecha
            CampoFechaSeleccion()

            // Balance
            Text("Balance", modifier = Modifier.padding(vertical = 8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total")
                        Text("\$XXXX")
                    }
                    Divider(Modifier.padding(vertical = 8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            Text("Ingresos", color = Color.Green)
                            Text("\$XXXX", color = Color.Green)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            Text("Egresos", color = Color.Red)
                            Text("\$XXXX", color = Color.Red)
                        }
                    }
                    Divider(Modifier.padding(vertical = 8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { /* Acción ver detalles */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B))
                        ) {
                            Text("Ver detalles", color = Color.Black)
                        }
                        Button(
                            onClick = { /* Acción PDF */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B))
                        ) {
                            Text("Convertir a PDF", color = Color.Black)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Selector de ingresos/egresos
            SelectorOpciones(
                opcion1 = "Ingresos",
                opcion2 = "Egresos",
                seleccionado = ""
            ) { /* TODO: Función de selección de valor */ }

            Spacer(modifier = Modifier.height(12.dp))

            // Lista de movimientos
            ListaMovimientos()

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { /* Acción reporte */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B)),
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 8.dp,
                    start = 246.dp
                )
            ) {
                Text("Reporte", color = Color.Black)
            }

            // Botones de agregar venta/compra
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Acción agregar venta */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB9F6CA))
                ) {
                    Text("Agregar venta", color = Color.Black)
                }
                Button(
                    onClick = { /* Acción agregar compra */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8A80))
                ) {
                    Text("Agregar compra", color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun ListaMovimientos(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        repeat(2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource(R.drawable.inventario),
                        contentDescription = "Producto",
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("X Producto1,\nY Producto 2")
                    Text("Fecha de Venta", style = MaterialTheme.typography.labelSmall)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("$ XXXX")
                    Text("Efectivo")
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVisualizarHistorialScreen() {
    val navController = rememberNavController()
    VisualizarHistorialScreen(navController = navController)
}