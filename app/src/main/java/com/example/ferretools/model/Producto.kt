package com.example.ferretools.model

data class Producto(
    val nombre: String,
    val descripcion: String? = null,
    val precio: Double,
    val cantidad_disponible: Int,
    val codigo_barras: String,
    val categoria_id: String? = null,
)
