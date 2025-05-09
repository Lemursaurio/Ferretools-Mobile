package com.example.ferretools.ui.producto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AgregarProductoScreen(
    navController: NavController,
    viewModel: ProductoViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    val showDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header fijo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF22D366))
                .padding(vertical = 12.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.List, contentDescription = "Atrás", tint = Color.Black)
            }
            Text(
                text = "Agregar Producto",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        // Formulario desplazable
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón cargar imagen
            Button(
                onClick = { /* TODO: cargar imagen */ },
                modifier = Modifier
                    .size(width = 120.dp, height = 60.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.List, contentDescription = "Cargar Imagen", tint = Color.Black)
                    Text("Cargar Imagen", color = Color.Black, fontSize = 14.sp)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Código de barras
            Text("Codigo de barras", fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = viewModel.codigoBarras.value,
                    onValueChange = { viewModel.codigoBarras.value = it },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = Color(0xFFE0E0E0), focusedContainerColor = Color(0xFFE0E0E0))
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { /* TODO: escanear */ }) {
                    Icon(Icons.Default.List, contentDescription = "Escanear", tint = Color.Black)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Nombre de producto
            Text("Nombre de Producto", fontWeight = FontWeight.Bold)
            TextField(
                value = viewModel.nombreProducto.value,
                onValueChange = { viewModel.nombreProducto.value = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color(0xFFE0E0E0), focusedContainerColor = Color(0xFFE0E0E0))
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Precio
            Text("Precio", fontWeight = FontWeight.Bold)
            TextField(
                value = viewModel.precio.value,
                onValueChange = { viewModel.precio.value = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color(0xFFE0E0E0), focusedContainerColor = Color(0xFFE0E0E0))
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Cantidad disponible
            Text("Cantidad disponible", fontWeight = FontWeight.Bold)
            TextField(
                value = viewModel.cantidad.value,
                onValueChange = { viewModel.cantidad.value = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color(0xFFE0E0E0), focusedContainerColor = Color(0xFFE0E0E0))
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Categoría (Dropdown)
            Text("Categoria", fontWeight = FontWeight.Bold)
            var expanded by remember { mutableStateOf(false) }
            Box {
                OutlinedButton(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (viewModel.categoriaSeleccionada.value.isEmpty()) "Selecciona una categoría"
                        else viewModel.categoriaSeleccionada.value
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    viewModel.categorias.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat.nombre) },
                            onClick = {
                                viewModel.categoriaSeleccionada.value = cat.nombre
                                expanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Descripción
            Text("Descripcion", fontWeight = FontWeight.Bold)
            TextField(
                value = viewModel.descripcion.value,
                onValueChange = { viewModel.descripcion.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color(0xFFE0E0E0), focusedContainerColor = Color(0xFFE0E0E0))
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
        // Botón grande fijo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { 
                    viewModel.agregarProducto()
                    showDialog.value = true 
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Agregar producto", color = Color.White, fontSize = 18.sp)
            }
        }
        // Diálogo de confirmación
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Producto Agregado") },
                text = { Text("El producto ha sido agregado correctamente.") },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog.value = false 
                            viewModel.limpiarFormulario()
                            navController.popBackStack()
                        }
                    ) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }
} 