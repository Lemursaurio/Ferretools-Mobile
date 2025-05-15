package com.example.ferretools.ui.inventario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
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
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.ferretools.ui.components.*
import com.example.ferretools.navigation.AppRoutes

@Composable
fun I_01_ListaProductos(
    navController: NavController,
    viewModel: InventarioViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header (fijo)
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
        // Botón de reportes (fijo)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { navController.navigate(AppRoutes.Inventory.INVENTORY_REPORT) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5F5F5)),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(horizontal = 18.dp, vertical = 4.dp)
            ) {
                Icon(Icons.Default.List, contentDescription = "Reportes", tint = Color.Black)
                Spacer(modifier = Modifier.width(6.dp))
                Text("Reportes", color = Color.Black)
            }
        }
        // Contenido desplazable
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            // Resumen
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SummaryCard(title = "Total de Productos", value = "0")
                SummaryCard(title = "Total de Productos", value = "0 PEN")
            }
            Spacer(modifier = Modifier.height(10.dp))
            // Chips de categorías
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AssistChip(
                    onClick = { /* TODO */ },
                    label = { Text("Todas las categorías", color = Color.White) },
                    colors = AssistChipDefaults.assistChipColors(containerColor = Color(0xFF7B6CA7))
                )
                AssistChip(
                    onClick = { /* TODO */ },
                    label = { Text("Categoría 1", color = Color.White) },
                    colors = AssistChipDefaults.assistChipColors(containerColor = Color(0xFFB6A7E6))
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            // Lista de productos
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                repeat(2) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.LightGray),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Person, contentDescription = "Imagen producto", tint = Color.DarkGray)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("{Nombre del Producto}", fontWeight = FontWeight.Bold)
                                Text("S/ {precio}")
                                Text("{cantidad} disponibles", fontSize = 13.sp)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        // Botones grandes (fijos)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate(AppRoutes.Inventory.ADD_PRODUCT) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Agregar producto", color = Color.White, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate(AppRoutes.Inventory.LIST_CATEGORIES) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Categorías", color = Color.White, fontSize = 18.sp)
            }
        }
        // Barra de navegación inferior (fija)
        AdminBottomNavBar(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun I_01_ListaProductosPreview() {
    val navController = rememberNavController()
    I_01_ListaProductos(navController = navController)
}