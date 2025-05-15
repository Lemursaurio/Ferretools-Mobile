package com.example.ferretools.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*

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

private val GreenPrimary = Color(0xFF1B5E20)
private val LightGrayBackground = Color(0xFFF5F5F5)
private val GrayPlaceholder = Color(0xFFBDBDBD)
private val ButtonGreen = Color(0xFF2E7D32)

@Composable
fun RegisterBusinessScreen(
    onFinish: (businessName: String, businessType: String, address: String, ruc: String, logoUri: String?) -> Unit,
    isLoading: Boolean = false,
    errorMessage: String? = null
) {
    var businessName by remember { mutableStateOf("") }
    var businessType by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var ruc by remember { mutableStateOf("") }
    var logoUri by remember { mutableStateOf<String?>(null) }

    val isFormValid = businessName.isNotBlank() && businessType.isNotBlank() && address.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGrayBackground)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Detalles del negocio",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = GreenPrimary,
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
        Divider(color = GrayPlaceholder.copy(alpha = 0.5f), thickness = 1.dp)
        Spacer(modifier = Modifier.height(24.dp))

        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = { onFinish(businessName, businessType, address, ruc, logoUri) },
            enabled = isFormValid && !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.buttonElevation(4.dp)
        ) {
            Text(
                text = "FINALIZAR REGISTRO",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
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
            color = GreenPrimary,
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = GrayPlaceholder) },
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        )
    }
}

@Composable
fun BusinessLogoPicker(logoUri: String?, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .clip(CircleShape)
            .background(Color(0xFFE0E0E0))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = if (logoUri == null) "Agregar logo del negocio" else "Logo del negocio",
            tint = Color(0xFF757575),
            modifier = Modifier.size(36.dp)
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
