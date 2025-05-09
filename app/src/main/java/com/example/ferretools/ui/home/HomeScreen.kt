package com.example.ferretools.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ferretools.ui.components.*

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF22D366))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.align(Alignment.Center))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text("Nombre de Usuario", color = Color.White, fontWeight = FontWeight.Bold)
                Text("Nombre de la Tienda", color = Color.White, fontSize = 13.sp)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        // Resumen
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SummaryCard(title = "Ventas de esta semana", value = "0", modifier = Modifier.weight(1f))
            SummaryCard(title = "Ingresos de esta semana", value = "0 PEN", modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(18.dp))
        // Accesos Directos
        Text(
            text = "Accesos Directos",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ShortcutButton("Venta", Icons.Default.ShoppingCart, Color(0xFF22D366))
            ShortcutButton("Gasto", Icons.Default.Person, Color(0xFF22D366))
            ShortcutButton("Inventario", Icons.Default.List, Color(0xFF22D366))
        }
        Spacer(modifier = Modifier.height(12.dp))
        // Alertas de Stock
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Alertas de Stock", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Button(
                onClick = { /* TODO: Acción de reporte */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFE082)),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp)
            ) {
                Text("Reporte", color = Color.Black, fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            StockAlertCard("Producto Crítico", 0, Color(0xFFFF8A80))
            StockAlertCard("Producto Bajo", 0, Color(0xFFFFF176))
            StockAlertCard("Producto Bajo 2", 0, Color(0xFFFFF176))
        }
        Box(modifier = Modifier.weight(1f)) {}
        // Barra de navegación inferior
        BottomNavBar(navController)
    }
} 