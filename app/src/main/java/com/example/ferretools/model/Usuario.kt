package com.example.ferretools.model

import com.example.ferretools.model.enums.RolUsuario

data class Usuario(
    val nombre: String,
    val correo: String,
    val celular: String,
    val contrasena: String,
    val rol: RolUsuario,
    val negocio: String? = null,
)
