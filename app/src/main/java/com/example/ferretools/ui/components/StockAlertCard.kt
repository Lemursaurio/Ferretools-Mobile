package com.example.ferretools.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StockAlertCard(productName: String, units: Int, bgColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("$productName", fontWeight = FontWeight.Bold, color = Color.Black)
            Text("Quedan $units unidades.", color = Color.Black)
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun StockAlertCardPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Ejemplo de alerta crítica (rojo)
        StockAlertCard(
            productName = "Martillo profesional",
            units = 2,
            bgColor = Color(0xFFFF8A80) // Rojo claro
        )

        // Ejemplo de alerta moderada (amarillo)
        StockAlertCard(
            productName = "Tornillos 5mm",
            units = 5,
            bgColor = Color(0xFFFFF176) // Amarillo claro
        )

        // Ejemplo de stock normal (verde)
        StockAlertCard(
            productName = "Pintura blanca",
            units = 15,
            bgColor = Color(0xFF81C784) // Verde claro
        )
    }
}