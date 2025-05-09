package com.example.ferretools.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.runtime.*
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
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EditBusinessScreen(
    initialBusinessName: String,
    initialBusinessType: String,
    initialAddress: String,
    initialRuc: String,
    initialLogo: String? = null,
    onBack: () -> Unit,
    onSave: (businessName: String, businessType: String, address: String, ruc: String, logo: String?) -> Unit,
    isLoading: Boolean = false,
    errorMessage: String? = null
) {
    var businessName by remember { mutableStateOf(initialBusinessName) }
    var businessType by remember { mutableStateOf(initialBusinessType) }
    var address by remember { mutableStateOf(initialAddress) }
    var ruc by remember { mutableStateOf(initialRuc) }
    var logo by remember { mutableStateOf(initialLogo) }
    var showSuccess by remember { mutableStateOf(false) }

    val areFieldsFilled = businessName.isNotBlank() && businessType.isNotBlank() && address.isNotBlank()
    val isFormValid = areFieldsFilled

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top bar: Back
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
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Editar Datos del Negocio",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        // Logo del negocio (opcional)
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E0E0))
                .clickable { /* TODO: Seleccionar logo */ },
            contentAlignment = Alignment.Center
        ) {
            if (logo != null) {
                // Aquí puedes cargar la imagen seleccionada
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Logo del negocio",
                    tint = Color(0xFF757575),
                    modifier = Modifier.size(36.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Agregar logo del negocio",
                    tint = Color(0xFF757575),
                    modifier = Modifier.size(36.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Campos del formulario
        EditBusinessFormField(
            label = "Nombre del negocio",
            value = businessName,
            onValueChange = { businessName = it },
            placeholder = "Nombre de la Empresa"
        )
        Spacer(modifier = Modifier.height(16.dp))
        EditBusinessFormField(
            label = "Rubro del negocio",
            value = businessType,
            onValueChange = { businessType = it },
            placeholder = "Ferretería, farmacia, etc."
        )
        Spacer(modifier = Modifier.height(16.dp))
        EditBusinessFormField(
            label = "Dirección",
            value = address,
            onValueChange = { address = it },
            placeholder = "Dirección del negocio"
        )
        Spacer(modifier = Modifier.height(16.dp))
        EditBusinessFormField(
            label = "RUC o número de identificación (opcional)",
            value = ruc,
            onValueChange = { ruc = it },
            placeholder = "RUC o ID",
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(24.dp))

        Divider(
            color = Color(0xFFE0E0E0),
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        if (showSuccess) {
            Text(
                text = "¡Datos del negocio guardados exitosamente!",
                color = Color(0xFF2E7D32),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Button(
            onClick = {
                showSuccess = true
                onSave(businessName, businessType, address, ruc, logo)
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
            Text(
                text = "GUARDAR",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
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
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditBusinessScreenPreview() {
    EditBusinessScreen(
        initialBusinessName = "Ferretería Central",
        initialBusinessType = "Ferretería",
        initialAddress = "Av. Principal 123",
        initialRuc = "12345678901",
        onBack = {},
        onSave = { _, _, _, _, _ -> },
        isLoading = false,
        errorMessage = null
    )
}