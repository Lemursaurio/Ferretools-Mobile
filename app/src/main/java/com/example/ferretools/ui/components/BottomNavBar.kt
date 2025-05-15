package com.example.ferretools.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.ferretools.R

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun BottomNavBar(navController: NavController, modifier: Modifier = Modifier) {
    NavigationBar(containerColor = Color(0xFF00BF59)) {
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Image(painterResource(R.drawable.inicio), contentDescription = "Inicio") },
            label = { Text("Inicio",
                fontSize = 16.sp, // Tamaño aumentado (original era ~12.sp)
                fontWeight = FontWeight.Bold // Opcional: para mejor legibilidad
            ) },
            modifier = modifier
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Image(painterResource(R.drawable.documento), contentDescription = "Balance") },
            label = { Text("Balance",
                fontSize = 16.sp, // Tamaño aumentado (original era ~12.sp)
                fontWeight = FontWeight.Bold // Opcional: para mejor legibilidad
            ) },
            modifier = modifier
        )
        NavigationBarItem(
            selected = false,
            onClick = {  },
            icon = { Image(painterResource(R.drawable.inventario), contentDescription = "Inventario") },
            label = { Text("Inventario",
                fontSize = 16.sp, // Tamaño aumentado (original era ~12.sp)
                fontWeight = FontWeight.Bold // Opcional: para mejor legibilidad
            )},
            modifier = modifier
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Image(painterResource(R.drawable.cuenta), contentDescription = "Cuenta") },
            label = { Text("Cuenta",
                fontSize = 16.sp, // Tamaño aumentado (original era ~12.sp)
                fontWeight = FontWeight.Bold // Opcional: para mejor legibilidad
            ) },
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun BottomNavBarPreview() {
    // Simulamos un NavController para la preview
    val mockNavController = rememberNavController()

    BottomNavBar(
        navController = mockNavController,
        modifier = Modifier
    )
}
