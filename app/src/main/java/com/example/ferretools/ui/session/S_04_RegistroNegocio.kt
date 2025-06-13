package com.example.ferretools.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.model.database.Usuario
import com.example.ferretools.model.enums.RolUsuario
import com.example.ferretools.navigation.AppRoutes
import com.example.ferretools.theme.FerretoolsTheme

@Composable
fun S_04_RegistroNegocio(
    navController: NavController,
    newUser: Usuario,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    // viewModel: RegistroNegocioViewModel = viewModel() // Para uso futuro
) {
    var businessName by remember { mutableStateOf("") }
    var businessType by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var ruc by remember { mutableStateOf("") }
    val logoUri by remember { mutableStateOf<String?>(null) }

    val isFormValid = businessName.isNotBlank() && businessType.isNotBlank() && address.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Detalles del negocio",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        BusinessLogoPicker(logoUri = logoUri, onClick = {
            // TODO: Lógica para seleccionar imagen
        })

        Spacer(modifier = Modifier.height(24.dp))

        BusinessFormField("Nombre del negocio", businessName, { businessName = it }, "Nombre de la Empresa")
        Spacer(modifier = Modifier.height(16.dp))
        BusinessFormField("Rubro del negocio", businessType, { businessType = it }, "Ferretería, farmacia, etc.")
        Spacer(modifier = Modifier.height(16.dp))
        BusinessFormField("Dirección", address, { address = it }, "Dirección")
        Spacer(modifier = Modifier.height(16.dp))
        BusinessFormField("R.U.C.", ruc, { ruc = it }, "R.U.C.")

        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider(color = MaterialTheme.colorScheme.outline)
        Spacer(modifier = Modifier.height(24.dp))

        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.onError,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = {
                // Aquí puedes llamar a tu ViewModel o lógica de registro
                // viewModel.registerBusiness(businessName, businessType, address, ruc, logoUri)
                // Por ahora, navega a la pantalla de login
                navController.navigate(AppRoutes.Auth.LOGIN)
            },
            enabled = isFormValid && !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = MaterialTheme.shapes.small,
            elevation = ButtonDefaults.buttonElevation(4.dp)
        ) {
            Text(
                text = "FINALIZAR REGISTRO",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun BusinessFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = MaterialTheme.colorScheme.onSurfaceVariant) },
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun BusinessLogoPicker(logoUri: String?, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceDim)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = if (logoUri == null) "Agregar logo del negocio" else "Logo del negocio",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(54.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun S_04_RegistroNegocioPreview() {
    FerretoolsTheme {
        val navController = rememberNavController()
        S_04_RegistroNegocio(
            navController = navController,
            newUser = Usuario(
                nombre = "",
                correo = "",
                celular = "",
                contrasena = "",
                rol = RolUsuario.ADMIN
            )
        )
    }

}