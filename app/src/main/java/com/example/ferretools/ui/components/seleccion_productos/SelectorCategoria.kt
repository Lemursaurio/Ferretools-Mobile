package com.example.ferretools.ui.components.seleccion_productos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SelectorCategoria(modifier: Modifier = Modifier) {
    Text("Categoría")
    Spacer(modifier = Modifier.height(4.dp))
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("Seleccionar categorías") }
    Box {
        OutlinedButton(
            onClick = { expanded = true },
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                selectedCategory,
                color = Color.Black
            )
            Icon(
                Icons.Default.ArrowDropDown,
                tint = Color.Black,
                contentDescription = null
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                selectedCategory = "Categoría 1"
                expanded = false
            },
                text = { Text("Categoría 1") }
            )
            DropdownMenuItem(onClick = {
                selectedCategory = "Categoría 2"
                expanded = false
            },
                text = { Text("Categoría 2") }
            )
        }
    }
}