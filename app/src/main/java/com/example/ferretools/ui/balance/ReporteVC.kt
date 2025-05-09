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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.R
import com.example.ferretools.ui.components.SelectorOpciones
import com.example.ferretools.ui.components.TopNavBar

@Composable
fun ReportesVentasComprasScreen(navController: NavController) {
    Scaffold(
        topBar = { TopNavBar(navController, "Reporte de Ventas y Compras") }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Selector de ventas/compras
            SelectorOpciones(
                opcion1 = "Compras",
                opcion2 = "Ventas",
                seleccionado = ""
            ) { /* TODO: Función de selección de valor */ }

            Spacer(Modifier.padding(vertical = 8.dp))

            // Box del gráfico
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(240.dp) // Aumenta la altura si es necesario
                    .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Estadísticas de Venta", fontWeight = FontWeight.Bold)
                        // Selector de periodo
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Monthly", fontSize = 13.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    // Placeholder del gráfico
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        // Imagen de gráfico como placeholder
                        Image(
                            painter = painterResource(R.drawable.grafico),
                            contentDescription = "Gráfico",
                            modifier = Modifier.size(400.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    // Botón PDF
                    Button(
                        onClick = { /* Acción PDF */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B))
                    ) {
                        Text("Convertir a PDF", color = Color.Black)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cuadros de resumen
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ResumenBox(
                        titulo = "Productos\ndiferentes",
                        valor = "XXXX",
                        porcentaje = "+15%"
                    )
                    ResumenBox(
                        titulo = "Categorías\ndiferentes",
                        valor = "XXXX",
                        porcentaje = "+15%"
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ResumenBox(
                        titulo = "Productos\ntotales",
                        valor = "XXXX",
                        porcentaje = "+15%"
                    )
                    ResumenBox(
                        titulo = "Ganancias\ntotales",
                        valor = "XXXX",
                        porcentaje = "+15%"
                    )
                }
            }
        }
    }
}

@Composable
fun ResumenBox(
    titulo: String,
    valor: String,
    porcentaje: String
) {
    Box(
        modifier = Modifier
            .width(156.dp)
            .height(150.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource(R.drawable.inventario),
                        contentDescription = "Indicador",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(titulo, fontSize = 13.sp)
            }
            Divider(Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(valor, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(
                    porcentaje,
                    color = Color(0xFF00C853),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReportesVentasComprasScreen() {
    val navController = rememberNavController()
    ReportesVentasComprasScreen(navController = navController)
}