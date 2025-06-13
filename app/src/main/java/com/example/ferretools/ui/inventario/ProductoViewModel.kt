package com.example.ferretools.ui.inventario

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
    
    // Variables para edición
    private var productoOriginal: Producto? = null
    private var categoriaOriginal: String = ""

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

    fun cargarProductoParaEditar(producto: Producto) {
        productoOriginal = producto
        categoriaOriginal = producto.categoria
        
        // Cargar los datos del producto en los campos
        codigoBarras.value = producto.codigoBarras
        nombreProducto.value = producto.nombre
        precio.value = producto.precio
        cantidad.value = producto.cantidad
        categoriaSeleccionada.value = producto.categoria
        descripcion.value = producto.descripcion
    }

    fun editarProducto(): Boolean {
        val productoOriginalRef = productoOriginal ?: return true
        
        // Crear el producto actualizado
        val productoActualizado = Producto(
            codigoBarras.value,
            nombreProducto.value,
            precio.value,
            cantidad.value,
            categoriaSeleccionada.value,
            descripcion.value
        )
        
        // Remover el producto de la categoría original
        val categoriaAnterior = categorias.find { it.nombre == categoriaOriginal }
        categoriaAnterior?.productos?.remove(productoOriginalRef)
        
        // Agregar el producto actualizado a la nueva categoría
        val categoriaNueva = categorias.find { it.nombre == categoriaSeleccionada.value }
        categoriaNueva?.productos?.add(productoActualizado)
        
        return true
    }

    fun limpiarFormulario() {
        nombreProducto.value = ""
        precio.value = ""
        cantidad.value = ""
        descripcion.value = ""
        codigoBarras.value = ""
        categoriaSeleccionada.value = ""
        productoOriginal = null
        categoriaOriginal = ""
    }
}