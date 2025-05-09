package com.example.ferretools.ui.session
/*
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.lifecycle.ViewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.ferretools.ui.theme.FerreToolsTheme
import com.example.ferretools.viewmodel.RegistroViewModel

/*
class RegistroViewModel : ViewModel() {
    var rolSeleccionado by mutableStateOf<String?>(null)
        private set

    fun seleccionarRol(rol: String) {
        rolSeleccionado = rol
    }
}*/

@Composable
fun SeleccionRolScreen(
    viewModel: RegistroViewModel = viewModel(),
    onSiguienteClick: () -> Unit
) {
    val rolSeleccionado = viewModel.rolSeleccionado

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¿Qué rol deseas registrar?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RolCard("Administrador", Icons.Default.Person, viewModel)
            RolCard("Cliente", Icons.Default.Person, viewModel)
            RolCard("Almacenero", Icons.Default.Person, viewModel)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onSiguienteClick() },
            enabled = rolSeleccionado != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("SIGUIENTE")
        }
    }
}

@Composable
fun RolCard(
    rol: String,
    icon: ImageVector,
    viewModel: RegistroViewModel
) {
    val isSelected = viewModel.rolSeleccionado == rol

    Card(
        modifier = Modifier
            .size(100.dp)
            .clickable { viewModel.seleccionarRol(rol) },
        /*backgroundColor = if (isSelected) Color(0xFF81C784) else Color.LightGray,*/
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(icon, contentDescription = rol, tint = Color.Black, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(rol, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
    }
}*/

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.tooling.preview.Preview
import com.example.ferretools.ui.theme.FerretoolsTheme

@Composable
fun SelectRoleScreen(
    onBack: () -> Unit,
    onNextClick: (UserRole) -> Unit
) {
    var selectedRole by remember { mutableStateOf<UserRole?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¿Qué rol deseas registrar?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E7D32),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RoleCard(
                title = "Administrador",
                icon = Icons.Filled.Person,
                selected = selectedRole == UserRole.ADMIN,
                onClick = { selectedRole = UserRole.ADMIN }
            )
            RoleCard(
                title = "Cliente",
                icon = Icons.Filled.Person,
                selected = selectedRole == UserRole.CLIENT,
                onClick = { selectedRole = UserRole.CLIENT }
            )
            RoleCard(
                title = "Almacenero",
                icon = Icons.Filled.Person,
                selected = selectedRole == UserRole.ALMACENERO,
                onClick = { selectedRole = UserRole.ALMACENERO }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { selectedRole?.let { onNextClick(it) } },
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
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = Color(0xFF333333)
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
    Card(
        modifier = Modifier
            .size(100.dp)
            .shadow(if (selected) 12.dp else 4.dp, RoundedCornerShape(16.dp))
            .background(if (selected) Color(0xFF2E7D32) else Color.White)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(if (selected) 8.dp else 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(if (selected) Color(0xFF2E7D32) else Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = if (selected) Color.White else Color(0xFF2E7D32),
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                color = if (selected) Color.White else Color(0xFF2E7D32),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

// Enum para los roles de usuario
enum class UserRole {
    ADMIN, CLIENT, ALMACENERO
}

@Preview(showSystemUi = false, showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun SelectRoleScreenPreview() {
    // Usa tu tema personalizado si lo tienes (reemplaza FerreToolsTheme con tu tema)
    FerretoolsTheme {
        SelectRoleScreen(
            onBack = {},
            onNextClick = { role ->
                // Acción para probar
                println("Rol seleccionado: $role")
            }
        )
    }
}