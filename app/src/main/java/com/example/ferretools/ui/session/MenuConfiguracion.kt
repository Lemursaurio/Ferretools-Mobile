package com.example.ferretools.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AccountSettingsScreen(
    userName: String,
    userLastName: String,
    userPhone: String,
    userEmail: String,
    isAdmin: Boolean = false,
    onBack: () -> Unit,
    onEditProfile: () -> Unit,
    onChangeQr: () -> Unit,
    onChangePassword: () -> Unit,
    onLogout: () -> Unit,
    onEditBusiness: (() -> Unit)? = null,
    onToggleDarkMode: (Boolean) -> Unit,
    onToggleStockNotification: (Boolean) -> Unit,
    darkModeEnabled: Boolean,
    stockNotificationEnabled: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        // Top bar: Back and Edit
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color(0xFF333333)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onEditProfile) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar perfil",
                    tint = Color(0xFF2E7D32)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // User info
        Text(
            text = "$userName $userLastName",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Contact info
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Celular",
                tint = Color(0xFF555555),
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = userPhone,
                fontSize = 16.sp,
                color = Color(0xFF555555)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Correo",
                tint = Color(0xFF555555),
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = userEmail,
                fontSize = 16.sp,
                color = Color(0xFF555555)
            )
        }

        Divider(
            color = Color(0xFFE0E0E0),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Opciones de configuración
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            // Solo para administrador: Editar datos del negocio
            if (isAdmin && onEditBusiness != null) {
                Divider(
                    color = Color(0xFFE0E0E0),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                SettingsItem(
                    icon = Icons.Default.Warehouse,
                    text = "Editar datos del negocio",
                    color = Color(0xFF2E7D32),
                    onClick = onEditBusiness
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            // Cambiar QR de Yape
            SettingsItem(
                icon = Icons.Default.QrCode,
                text = "Cambiar QR de Yape",
                color = Color(0xFF16A34A),
                onClick = onChangeQr
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Modo oscuro
            SettingsSwitchItem(
                icon = Icons.Default.DarkMode,
                text = "Modo oscuro",
                checked = darkModeEnabled,
                onCheckedChange = onToggleDarkMode
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Notificación de Stock bajo
            SettingsSwitchItem(
                icon = Icons.Default.Notifications,
                text = "Notificación de Stock bajo",
                checked = stockNotificationEnabled,
                onCheckedChange = onToggleStockNotification
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Cambiar contraseña
            SettingsItem(
                icon = Icons.Default.Lock,
                text = "Cambiar Contraseña",
                color = Color(0xFF2563EB),
                onClick = onChangePassword
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Cerrar sesión
            SettingsItem(
                icon = Icons.Default.ExitToApp,
                text = "Cerrar Sesión",
                color = Color(0xFFDC2626),
                onClick = onLogout
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun SettingsItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            color = color,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SettingsSwitchItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color(0xFF333333),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color(0xFF333333),
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFF16A34A),
                checkedTrackColor = Color(0xFFBBF7D0)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccountSettingsScreenPreview() {
    var darkMode by remember { mutableStateOf(false) }
    var stockNotif by remember { mutableStateOf(true) }
    AccountSettingsScreen(
        userName = "Juan",
        userLastName = "Pérez",
        userPhone = "+51 999 888 777",
        userEmail = "juan.perez@email.com",
        isAdmin = true,
        onBack = {},
        onEditProfile = {},
        onChangeQr = {},
        onChangePassword = {},
        onLogout = {},
        onEditBusiness = {},
        onToggleDarkMode = { darkMode = it },
        onToggleStockNotification = { stockNotif = it },
        darkModeEnabled = darkMode,
        stockNotificationEnabled = stockNotif
    )
}