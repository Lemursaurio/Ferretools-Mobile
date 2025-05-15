package com.example.ferretools.ui.configuracion

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Config_03_EditarNegocio(
    navController: NavController,
    initialBusinessName: String,
    initialBusinessType: String,
    initialAddress: String,
    initialRuc: String,
    initialLogo: String? = null,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    // viewModel: EditarNegocioViewModel = viewModel() // Para uso futuro
) {
    var businessName by rememberSaveable { mutableStateOf(initialBusinessName) }
    var businessType by rememberSaveable { mutableStateOf(initialBusinessType) }
    var address by rememberSaveable { mutableStateOf(initialAddress) }
    var ruc by rememberSaveable { mutableStateOf(initialRuc) }
    var logo by rememberSaveable { mutableStateOf(initialLogo) }
    var showSuccess by remember { mutableStateOf(false) }

    val isFormValid = businessName.isNotBlank() && businessType.isNotBlank() && address.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color(0xFF333333))
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        Text(
            text = "Editar Datos del Negocio",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        LogoSelector(logo) {
            // TODO: lógica para seleccionar una imagen
        }

        Spacer(modifier = Modifier.height(24.dp))

        EditBusinessFormField("Nombre del negocio", businessName, { businessName = it }, "Nombre de la Empresa")
        Spacer(modifier = Modifier.height(16.dp))
        EditBusinessFormField("Rubro del negocio", businessType, { businessType = it }, "Ferretería, farmacia, etc.")
        Spacer(modifier = Modifier.height(16.dp))
        EditBusinessFormField("Dirección", address, { address = it }, "Dirección del negocio")
        Spacer(modifier = Modifier.height(16.dp))
        EditBusinessFormField("RUC o número de identificación (opcional)", ruc, { ruc = it }, "RUC o ID", KeyboardType.Number)

        Spacer(modifier = Modifier.height(24.dp))
        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
        Spacer(modifier = Modifier.height(24.dp))

        errorMessage?.let { ErrorText(it) }
        if (showSuccess) SuccessText("¡Datos del negocio guardados exitosamente!")

        Button(
            onClick = {
                showSuccess = true
                // Aquí puedes llamar a tu ViewModel o lógica de guardado
                // viewModel.saveBusiness(businessName, businessType, address, ruc, logo)
                navController.popBackStack() // O navega a donde corresponda tras guardar
            },
            enabled = isFormValid && !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D32),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(2.dp)
        ) {
            Text("GUARDAR", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Composable
fun LogoSelector(logo: String?, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .clip(CircleShape)
            .background(Color(0xFFE0E0E0))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.CameraAlt,
            contentDescription = "Logo del negocio",
            tint = Color(0xFF757575),
            modifier = Modifier.size(36.dp)
        )
        // Aquí puedes mostrar una imagen real si `logo` tiene una URI.
    }
}

@Composable
fun EditBusinessFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color(0xFF999999)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ErrorText(text: String) {
    Text(
        text = text,
        color = Color.Red,
        fontSize = 14.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun SuccessText(text: String) {
    Text(
        text = text,
        color = Color(0xFF2E7D32),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun Config_03_EditarNegocioPreview() {
    val navController = rememberNavController()
    Config_03_EditarNegocio(
        navController = navController,
        initialBusinessName = "Ferretería Central",
        initialBusinessType = "Ferretería",
        initialAddress = "Av. Principal 123",
        initialRuc = "12345678901"
    )
}