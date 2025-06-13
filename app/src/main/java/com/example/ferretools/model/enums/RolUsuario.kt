package com.example.ferretools.model.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
enum class RolUsuario(val titulo: String, val icon: ImageVector) {
    ADMIN("Administrador", Icons.Default.AdminPanelSettings),
    CLIENTE("Cliente", Icons.Default.Person),
    ALMACENERO("Almacenero", Icons.Default.Warehouse)
}