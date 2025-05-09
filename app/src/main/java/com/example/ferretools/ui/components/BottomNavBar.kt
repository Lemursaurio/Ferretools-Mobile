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

@Composable
fun BottomNavBar(navController: NavController, modifier: Modifier = Modifier) {
    NavigationBar(containerColor = Color(0xFF00BF59)) {
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Image(painterResource(R.drawable.inicio), contentDescription = "Inicio") },
            label = { Text("Inicio") },
            modifier = modifier
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Image(painterResource(R.drawable.documento), contentDescription = "Balance") },
            label = { Text("Balance") },
            modifier = modifier
        )
        NavigationBarItem(
            selected = false,
            onClick = {  },
            icon = { Image(painterResource(R.drawable.inventario), contentDescription = "Inventario") },
            label = { Text("Inventario") },
            modifier = modifier
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Image(painterResource(R.drawable.cuenta), contentDescription = "Cuenta") },
            label = { Text("Cuenta") },
            modifier = modifier
        )
    }
}