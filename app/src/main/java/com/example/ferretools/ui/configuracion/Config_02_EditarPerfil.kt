package com.example.ferretools.ui.configuracion

import android.util.Patterns
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
fun EditProfileScreen(
    initialName: String,
    initialLastName: String,
    initialPhone: String,
    initialEmail: String,
    initialProfileImage: String? = null,
    onBack: () -> Unit,
    onSave: (name: String, lastName: String, phone: String, email: String, profileImage: String?) -> Unit,
    isLoading: Boolean = false,
    errorMessage: String? = null
) {
    var name by remember { mutableStateOf(initialName) }
    var lastName by remember { mutableStateOf(initialLastName) }
    var phone by remember { mutableStateOf(initialPhone) }
    var email by remember { mutableStateOf(initialEmail) }
    var profileImage by remember { mutableStateOf(initialProfileImage) }
    var showSuccess by remember { mutableStateOf(false) }

    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val areFieldsFilled = name.isNotBlank() && lastName.isNotBlank() && phone.isNotBlank() && email.isNotBlank()
    val isFormValid = isEmailValid && areFieldsFilled

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(onBack)

        Text(
            text = "Editar Perfil",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        ProfileImage(profileImage) {
            // TODO: Acción para seleccionar imagen
        }

        Spacer(modifier = Modifier.height(24.dp))

        EditProfileFormField("Nombres", name, { name = it }, "Ingrese sus nombres")
        Spacer(modifier = Modifier.height(16.dp))
        EditProfileFormField("Apellidos", lastName, { lastName = it }, "Ingrese sus apellidos")
        Spacer(modifier = Modifier.height(16.dp))
        EditProfileFormField("Teléfono", phone, { phone = it }, "Teléfono", keyboardType = KeyboardType.Phone)
        Spacer(modifier = Modifier.height(16.dp))
        EditProfileFormField(
            label = "Correo electrónico",
            value = email,
            onValueChange = { email = it },
            placeholder = "Correo",
            keyboardType = KeyboardType.Email,
            isError = email.isNotBlank() && !isEmailValid,
            errorText = if (email.isNotBlank() && !isEmailValid) "Correo inválido" else null
        )

        Spacer(modifier = Modifier.height(24.dp))
        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
        Spacer(modifier = Modifier.height(24.dp))

        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        if (showSuccess) {
            Text(
                text = "¡Datos guardados exitosamente!",
                color = Color(0xFF2E7D32),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        SaveButton(
            enabled = isFormValid && !isLoading,
            onClick = {
                showSuccess = true
                onSave(name, lastName, phone, email, profileImage)
            }
        )
    }
}

@Composable
private fun Header(onBack: () -> Unit) {
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
}

@Composable
private fun ProfileImage(profileImage: String?, onClick: () -> Unit) {
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
            contentDescription = if (profileImage != null) "Imagen de perfil" else "Agregar imagen de perfil",
            tint = Color(0xFF757575),
            modifier = Modifier.size(36.dp)
        )
    }
}

@Composable
fun EditProfileFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    errorText: String? = null
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
            isError = isError,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier.fillMaxWidth()
        )
        if (isError && errorText != null) {
            Text(
                text = errorText,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Composable
private fun SaveButton(enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = enabled,
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

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(
        initialName = "Juan",
        initialLastName = "Pérez",
        initialPhone = "+51 999 888 777",
        initialEmail = "juan.perez@email.com",
        onBack = {},
        onSave = { _, _, _, _, _ -> }
    )
}
