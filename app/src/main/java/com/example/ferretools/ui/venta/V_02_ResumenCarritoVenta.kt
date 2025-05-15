package com.example.ferretools.ui.venta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.R
import com.example.ferretools.navigation.AppRoutes
import com.example.ferretools.ui.components.AdminBottomNavBar
import com.example.ferretools.ui.components.SelectorOpciones
import com.example.ferretools.ui.components.TopNavBar
import com.example.ferretools.ui.components.detalles_cv.CampoFechaSeleccion
import com.example.ferretools.ui.components.detalles_cv.ListaProductosSeleccionados

@Composable
fun V_02_ResumenCarritoVenta(
    navController: NavController,
    // viewModel: ResumenCarritoVentaViewModel = viewModel() // Para uso futuro
) {
    Scaffold(
        topBar = { TopNavBar(navController, "Detalles de venta") },
        bottomBar = { AdminBottomNavBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Fecha de venta
            Text("Fecha de venta")

            // Selección de fecha
            CampoFechaSeleccion()

            Text(
                "DD/MM/YYYY",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Medio de pago", Modifier.padding(vertical = 8.dp))

            // Medio de pago
            SelectorOpciones(
                opcion1 = "Efectivo",
                opcion2 = "Yape",
                opcion2Img = R.drawable.yape,
                seleccionado = ""
            ) { /* TODO: Función de selección de valor */ }

            Spacer(modifier = Modifier.height(16.dp))

            Divider(Modifier.padding(vertical = 18.dp))

            // Lista de productos
            ListaProductosSeleccionados()

            Spacer(modifier = Modifier.height(52.dp))

            Divider()

            // Total
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total")
                Text("$ XXXX")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Confirmar Venta
            Button(
                onClick = { navController.navigate(AppRoutes.Sale.RECEIPT) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B))
            ) {
                Text("Confirmar Venta", color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun V_02_ResumenCarritoVentaPreview() {
    val navController = rememberNavController()
    V_02_ResumenCarritoVenta(navController = navController)
}