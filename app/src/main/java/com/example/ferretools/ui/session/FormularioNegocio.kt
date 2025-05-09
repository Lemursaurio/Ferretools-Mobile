package com.example.ferretools.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RegisterBusinessScreen(
    onFinish: (businessName: String, businessType: String, managerName: String, ruc: String, logoUri: String?) -> Unit,
    isLoading: Boolean = false,
    errorMessage: String? = null
) {
    var businessName by remember { mutableStateOf("") }
    var businessType by remember { mutableStateOf("") }
    var managerName by remember { mutableStateOf("") }
    var ruc by remember { mutableStateOf("") }
    var logoUri by remember { mutableStateOf<String?>(null) }

    // Validación simple: nombre y tipo de negocio obligatorios
    val isFormValid = businessName.isNotBlank() && businessType.isNotBlank() && managerName.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Detalles del negocio",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
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
            if (logoUri != null) {
                // Aquí puedes cargar el logo seleccionado
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Logo del negocio",
                    tint = Color(0xFF757575),
                    modifier = Modifier.size(36.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Agregar logo del negocio",
                    tint = Color(0xFF757575),
                    modifier = Modifier.size(36.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Campos del formulario
        BusinessFormField(
            label = "Nombre del negocio",
            value = businessName,
            onValueChange = { businessName = it },
            placeholder = "Nombre de la Empresa"
        )
        Spacer(modifier = Modifier.height(16.dp))
        BusinessFormField(
            label = "Rubro del negocio",
            value = businessType,
            onValueChange = { businessType = it },
            placeholder = "Ferretería, farmacia, etc."
        )
        Spacer(modifier = Modifier.height(16.dp))
        BusinessFormField(
            label = "Dirección",
            value = managerName,
            onValueChange = { managerName = it },
            placeholder = "Dirección"
        )
        Spacer(modifier = Modifier.height(16.dp))
        BusinessFormField(
            label = "R.U.C.",
            value = ruc,
            onValueChange = { ruc = it },
            placeholder = "R.U.C."
        )

        Spacer(modifier = Modifier.height(24.dp))

        Divider(
            color = Color(0xFFBDBDBD).copy(alpha = 0.5f),
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

        Button(
            onClick = {
                onFinish(businessName, businessType, managerName, ruc, logoUri)
            },
            enabled = isFormValid && !isLoading,
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
                text = "FINALIZAR REGISTRO",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
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
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20),
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color(0xFFBDBDBD)) },
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterBusinessScreenPreview() {
    RegisterBusinessScreen(
        onFinish = { _, _, _, _, _ -> },
        isLoading = false,
        errorMessage = null
    )
}