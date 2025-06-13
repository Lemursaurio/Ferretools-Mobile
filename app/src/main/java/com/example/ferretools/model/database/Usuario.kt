package com.example.ferretools.model.database

import android.net.Uri
import com.example.ferretools.model.enums.RolUsuario
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Usuario(
    val nombre: String,
    val correo: String,
    val celular: String,
    val contrasena: String,
    @Contextual val foto: Uri? = null,
    val rol: RolUsuario,
    val negocio: String? = null,
)
