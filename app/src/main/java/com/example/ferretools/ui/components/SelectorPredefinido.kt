package com.example.ferretools.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectorPredefinido(
    label: String,
    value: String,
    opciones: List<String>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = { },
            label = { Text(label) },
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Seleccionar")
                }
            },
            readOnly = true,
            isError = error != null,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            shape = RoundedCornerShape(8.dp)
        )
        
        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        onValueChange(opcion)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun SelectorEstado(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null
) {
    val estados = listOf("Activo", "Inactivo", "Descontinuado")
    SelectorPredefinido(
        label = "Estado",
        value = value,
        opciones = estados,
        onValueChange = onValueChange,
        modifier = modifier,
        error = error
    )
}

@Composable
fun SelectorCategoria(
    value: String,
    onValueChange: (String) -> Unit,
    categorias: List<String>,
    modifier: Modifier = Modifier,
    error: String? = null
) {
    SelectorPredefinido(
        label = "Categoría",
        value = value,
        opciones = categorias,
        onValueChange = onValueChange,
        modifier = modifier,
        error = error
    )
}

@Composable
fun SelectorUnidadMedida(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null
) {
    val unidades = listOf("Unidad", "Kilogramo", "Litro", "Metro", "Caja", "Paquete")
    SelectorPredefinido(
        label = "Unidad de Medida",
        value = value,
        opciones = unidades,
        onValueChange = onValueChange,
        modifier = modifier,
        error = error
    )
} 