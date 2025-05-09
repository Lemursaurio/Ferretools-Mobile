package com.example.ferretools.ui.components.detalles_cv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListaProductosSeleccionados(modifier: Modifier = Modifier) {
    repeat(3) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Producto", modifier = Modifier.size(40.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text("Nombre")
                Text("Precio")
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Cantidad")
                    Slider(
                        value = 0f,
                        onValueChange = {},
                        valueRange = 0f..10f,
                        modifier = Modifier.weight(0.8f).size(25.dp)
                    )
//                            Text("0-Disp.")
                }
            }
        }
    }
}