package com.example.ferretools.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ChangePasswordScreen(
    onPasswordChanged: () -> Unit,
    isLoading: Boolean = false,
    errorMessage: String? = null
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showNewPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }
    var showSuccess by remember { mutableStateOf(false) }

    // Validaciones
    val isPasswordValid = newPassword.length >= 6 // Puedes agregar más reglas aquí
    val doPasswordsMatch = newPassword == confirmPassword
    val isFormValid = isPasswordValid && doPasswordsMatch

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Cambiar Contraseña",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            textAlign = TextAlign.Center
        )

        // Nueva contraseña
        PasswordFormField(
            label = "Ingrese su nueva contraseña",
            value = newPassword,
            onValueChange = { newPassword = it },
            placeholder = "Contraseña",
            showPassword = showNewPassword,
            onTogglePassword = { showNewPassword = !showNewPassword },
            isError = newPassword.isNotBlank() && !isPasswordValid,
            errorText = if (newPassword.isNotBlank() && !isPasswordValid) "Mínimo 6 caracteres" else null
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Confirmar nueva contraseña
        PasswordFormField(
            label = "Confirme su nueva contraseña",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = "Repetir contraseña",
            showPassword = showConfirmPassword,
            onTogglePassword = { showConfirmPassword = !showConfirmPassword },
            isError = confirmPassword.isNotBlank() && !doPasswordsMatch,
            errorText = if (confirmPassword.isNotBlank() && !doPasswordsMatch) "Las contraseñas no coinciden" else null
        )

        Spacer(modifier = Modifier.height(24.dp))

        Divider(
            color = Color(0xFFF0F0F0),
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
                text = "¡Contraseña cambiada exitosamente!",
                color = Color(0xFF2E7D32),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Button(
            onClick = {
                showSuccess = true
                onPasswordChanged()
            },
            enabled = isFormValid && !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(2.dp)
        ) {
            Text(
                text = "CONTINUAR",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun PasswordFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    showPassword: Boolean,
    onTogglePassword: () -> Unit,
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
            visualTransformation = if (!showPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = onTogglePassword) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (showPassword) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
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

@Preview(showBackground = true)
@Composable
fun ChangePasswordScreenPreview() {
    ChangePasswordScreen(
        onPasswordChanged = {},
        isLoading = false,
        errorMessage = null
    )
}