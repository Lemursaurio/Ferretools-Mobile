package com.example.ferretools.ui.configuracion

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color(0xFF333333))
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onEditProfile) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar perfil", tint = Color(0xFF2E7D32))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // User Info
        Text(
            text = "$userName $userLastName",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        UserContactInfo(icon = Icons.Default.Phone, value = userPhone)
        UserContactInfo(icon = Icons.Default.Email, value = userEmail)

        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

        Column(modifier = Modifier.fillMaxWidth()) {

            if (isAdmin && onEditBusiness != null) {
                SettingsItem(
                    icon = Icons.Default.Warehouse,
                    text = "Editar datos del negocio",
                    color = Color(0xFF2E7D32),
                    onClick = onEditBusiness
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            SettingsItem(
                icon = Icons.Default.QrCode,
                text = "Cambiar QR de Yape",
                color = Color(0xFF16A34A),
                onClick = onChangeQr
            )
            Spacer(modifier = Modifier.height(20.dp))

            SettingsSwitchItem(
                icon = Icons.Default.DarkMode,
                text = "Modo oscuro",
                checked = darkModeEnabled,
                onCheckedChange = onToggleDarkMode
            )
            Spacer(modifier = Modifier.height(20.dp))

            SettingsSwitchItem(
                icon = Icons.Default.Notifications,
                text = "Notificación de Stock bajo",
                checked = stockNotificationEnabled,
                onCheckedChange = onToggleStockNotification
            )
            Spacer(modifier = Modifier.height(20.dp))

            SettingsItem(
                icon = Icons.Default.Lock,
                text = "Cambiar Contraseña",
                color = Color(0xFF2563EB),
                onClick = onChangePassword
            )
            Spacer(modifier = Modifier.height(20.dp))

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
    icon: ImageVector,
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
        Icon(imageVector = icon, contentDescription = text, tint = color, modifier = Modifier.size(24.dp))
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
    icon: ImageVector,
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
        Icon(imageVector = icon, contentDescription = text, tint = Color(0xFF333333), modifier = Modifier.size(24.dp))
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

@Composable
fun UserContactInfo(icon: ImageVector, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF555555),
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = value, fontSize = 16.sp, color = Color(0xFF555555))
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
