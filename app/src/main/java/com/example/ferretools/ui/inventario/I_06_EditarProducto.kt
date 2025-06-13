package com.example.ferretools.ui.inventario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.ferretools.navigation.AppRoutes

@Composable
fun I_06_EditarProducto(
    navController: NavController,
    viewModel: ProductoViewModel,
    producto: Producto? = null
) {
    val scrollState = rememberScrollState()
    val showDialog = remember { mutableStateOf(false) }
    val showErrorDialog = remember { mutableStateOf(false) }
    
    // Cargar los datos del producto cuando se inicia la pantalla
    LaunchedEffect(producto) {
        producto?.let {
            viewModel.cargarProductoParaEditar(it)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header fijo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF22D366)) // Color azul para diferenciarlo de agregar
                .padding(vertical = 12.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack()},
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = Color.Black)
            }
            Text(
                text = "Editar Producto",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 4.dp, top = 30.dp,)
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
                    Text("Cambiar Imagen", color = Color.Black, fontSize = 14.sp, textAlign = TextAlign.Center)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Código de barras
            Text("Codigo de barras", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = viewModel.codigoBarras.value,
                    onValueChange = { viewModel.codigoBarras.value = it },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFE0E0E0), 
                        focusedContainerColor = Color(0xFFE0E0E0)
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { /* TODO: escanear */ }) {
                    Icon(Icons.Default.List, contentDescription = "Escanear", tint = Color.Black)
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Nombre de producto
            Text("Nombre de Producto", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
            TextField(
                value = viewModel.nombreProducto.value,
                onValueChange = { viewModel.nombreProducto.value = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFE0E0E0), 
                    focusedContainerColor = Color(0xFFE0E0E0)
                )
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Precio
            Text("Precio", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
            TextField(
                value = viewModel.precio.value,
                onValueChange = { viewModel.precio.value = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFE0E0E0), 
                    focusedContainerColor = Color(0xFFE0E0E0)
                )
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Cantidad disponible
            Text("Cantidad disponible", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
            TextField(
                value = viewModel.cantidad.value,
                onValueChange = { viewModel.cantidad.value = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFE0E0E0), 
                    focusedContainerColor = Color(0xFFE0E0E0)
                )
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Categoría (Dropdown)
            Text("Categoria", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
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
            Text("Descripcion", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
            TextField(
                value = viewModel.descripcion.value,
                onValueChange = { viewModel.descripcion.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFE0E0E0), 
                    focusedContainerColor = Color(0xFFE0E0E0)
                )
            )
            
            Spacer(modifier = Modifier.height(24.dp))
        }
        
        // Botones de acción
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Botón Cancelar
            Button(
                onClick = { 
                    viewModel.limpiarFormulario()
                    navController.popBackStack() 
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Cancelar", color = Color.White, fontSize = 16.sp)
            }
            
            // Botón Guardar Cambios
            Button(
                onClick = {
                    val success = viewModel.editarProducto()
                    if (success) {
                        showDialog.value = true
                    } else {
                        showErrorDialog.value = true
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF22D366)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Guardar Cambios", color = Color.White, fontSize = 16.sp)
            }
        }
    }
    
    // Diálogo de confirmación de éxito
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Producto Actualizado") },
            text = { Text("El producto ha sido actualizado correctamente.") },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog.value = false
                        viewModel.limpiarFormulario()
                        navController.popBackStack()
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
    
    // Diálogo de error
    if (showErrorDialog.value) {
        AlertDialog(
            onDismissRequest = { showErrorDialog.value = false },
            title = { Text("Error") },
            text = { Text("No se pudo actualizar el producto. Por favor, inténtalo de nuevo.") },
            confirmButton = {
                Button(
                    onClick = { showErrorDialog.value = false }
                ) {
                    Text("OK")
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun I_06_EditarProductoPreview() {
    val navController = rememberNavController()
    val viewModel: ProductoViewModel = viewModel()
    val productoEjemplo = Producto(
        codigoBarras = "123456789",
        nombre = "Martillo",
        precio = "25.50",
        cantidad = "10",
        categoria = "Ferretería",
        descripcion = "Martillo de acero para uso general"
    )
    I_06_EditarProducto(
        navController = navController,
        viewModel = viewModel,
        producto = productoEjemplo
    )
}


