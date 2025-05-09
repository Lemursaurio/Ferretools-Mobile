package com.example.ferretools.ui.producto

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf

data class Producto(
    val codigoBarras: String,
    val nombre: String,
    val precio: String,
    val cantidad: String,
    val categoria: String,
    val descripcion: String
)

data class Categoria(
    val nombre: String,
    val productos: MutableList<Producto> = mutableListOf()
)

class ProductoViewModel : ViewModel() {
    val nombreProducto = mutableStateOf("")
    val precio = mutableStateOf("")
    val cantidad = mutableStateOf("")
    val descripcion = mutableStateOf("")
    val codigoBarras = mutableStateOf("")

    // Lista de categorías y selección
    var categorias = mutableStateListOf(
        Categoria("Ferretería"),
        Categoria("Electricidad"),
        Categoria("Plomería")
    )
    var categoriaSeleccionada = mutableStateOf("")

    fun agregarProducto() {
        val producto = Producto(
            codigoBarras.value,
            nombreProducto.value,
            precio.value,
            cantidad.value,
            categoriaSeleccionada.value,
            descripcion.value
        )
        categorias.find { it.nombre == categoriaSeleccionada.value }?.productos?.add(producto)
    }

    fun limpiarFormulario() {
        nombreProducto.value = ""
        precio.value = ""
        cantidad.value = ""
        descripcion.value = ""
        codigoBarras.value = ""
        categoriaSeleccionada.value = ""
    }
} 