package com.example.ferretools.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ferretools.navigation.AppRoutes

import androidx.navigation.compose.rememberNavController

enum class RolUsuario2(val displayName: String, val icon: ImageVector) {
    ADMIN("Administrador", Icons.Default.AdminPanelSettings),
    CLIENTE("Cliente", Icons.Default.Person),
    ALMACENERO("Almacenero", Icons.Default.Warehouse)
}

@Composable
fun S_02_SeleccionRol(
    navController: NavController,
    modifier: Modifier = Modifier
    // viewModel: SeleccionRolViewModel = viewModel() // Para uso futuro
) {
    var selectedRole by remember { mutableStateOf<RolUsuario2?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Selecciona tu rol",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF2E7D32),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        RolUsuario2.values().forEach { rol ->
            RoleCard(
                title = rol.displayName,
                icon = rol.icon,
                selected = selectedRole == rol,
                onClick = { selectedRole = rol }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                when (selectedRole) {
                    RolUsuario2.ADMIN -> navController.navigate(AppRoutes.Auth.REGISTER_USER)
                    RolUsuario2.CLIENTE -> navController.navigate(AppRoutes.Auth.REGISTER_USER)
                    RolUsuario2.ALMACENERO -> navController.navigate(AppRoutes.Auth.REGISTER_USER)
                    null -> {} // No hacer nada si no hay selección
                }
            },
            enabled = selectedRole != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D32),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.buttonElevation(4.dp)
        ) {
            Text(
                text = "SIGUIENTE",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun RoleCard(
    title: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (selected) Color(0xFF2E7D32) else Color.White
    val contentColor = if (selected) Color.White else Color(0xFF2E7D32)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = contentColor,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                color = contentColor,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun S_02_SeleccionRolPreview() {
    val navController = rememberNavController()
    S_02_SeleccionRol(navController = navController)
}