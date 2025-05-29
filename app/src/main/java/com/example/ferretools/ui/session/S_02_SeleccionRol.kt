package com.example.ferretools.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.navigation.AppRoutes
import com.example.ferretools.theme.FerretoolsTheme

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
            .background(MaterialTheme.colorScheme.surface)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Selecciona tu rol",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(32.dp))

        RolUsuario2.entries.forEach { rol ->
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
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = MaterialTheme.shapes.small,
            elevation = ButtonDefaults.buttonElevation(4.dp)
        ) {
            Text(
                text = "SIGUIENTE",
                style = MaterialTheme.typography.labelSmall
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
    val backgroundColor = if (selected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.secondary

    val contentColor = if (selected) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSecondary

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
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
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun S_02_SeleccionRolPreview() {
    FerretoolsTheme {
        val navController = rememberNavController()
        S_02_SeleccionRol(navController = navController)
    }
}