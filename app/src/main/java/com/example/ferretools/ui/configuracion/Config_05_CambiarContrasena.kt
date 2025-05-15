package com.example.ferretools.ui.configuracion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

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
fun ChangePasswordSettingsScreen(
    onBack: () -> Unit,
    onPasswordChanged: () -> Unit,
    isLoading: Boolean = false,
    errorMessage: String? = null
) {
    var currentPassword by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var showCurrentPassword by rememberSaveable { mutableStateOf(false) }
    var showNewPassword by rememberSaveable { mutableStateOf(false) }
    var showConfirmPassword by rememberSaveable { mutableStateOf(false) }

    var showSuccess by rememberSaveable { mutableStateOf(false) }

    val isCurrentValid = currentPassword.length >= 6
    val isNewValid = newPassword.length >= 6 && newPassword != currentPassword
    val passwordsMatch = newPassword == confirmPassword
    val isFormValid = isCurrentValid && isNewValid && passwordsMatch

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(onBack)

        TitleText("Cambiar Contraseña")

        PasswordSettingsFormField(
            label = "Contraseña actual",
            value = currentPassword,
            onValueChange = { currentPassword = it },
            placeholder = "Contraseña actual",
            showPassword = showCurrentPassword,
            onTogglePassword = { showCurrentPassword = !showCurrentPassword },
            isError = currentPassword.isNotBlank() && !isCurrentValid,
            errorText = "Mínimo 6 caracteres".takeIf { currentPassword.isNotBlank() && !isCurrentValid }
        )

        Spacer(modifier = Modifier.height(20.dp))

        PasswordSettingsFormField(
            label = "Nueva contraseña",
            value = newPassword,
            onValueChange = { newPassword = it },
            placeholder = "Nueva contraseña",
            showPassword = showNewPassword,
            onTogglePassword = { showNewPassword = !showNewPassword },
            isError = newPassword.isNotBlank() && !isNewValid,
            errorText = when {
                newPassword == currentPassword -> "La nueva contraseña no puede ser igual a la actual"
                newPassword.length < 6 -> "Mínimo 6 caracteres"
                else -> null
            }.takeIf { newPassword.isNotBlank() }
        )

        Spacer(modifier = Modifier.height(20.dp))

        PasswordSettingsFormField(
            label = "Confirmar nueva contraseña",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = "Repetir nueva contraseña",
            showPassword = showConfirmPassword,
            onTogglePassword = { showConfirmPassword = !showConfirmPassword },
            isError = confirmPassword.isNotBlank() && !passwordsMatch,
            errorText = "Las contraseñas no coinciden".takeIf { confirmPassword.isNotBlank() && !passwordsMatch }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

        Spacer(modifier = Modifier.height(24.dp))

        errorMessage?.let {
            MessageText(it, color = Color.Red)
        }

        if (showSuccess) {
            MessageText("¡Contraseña cambiada exitosamente!", color = Color(0xFF2E7D32), bold = true)
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
private fun TopBar(onBack: () -> Unit) {
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
fun TitleText(text: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF333333),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun MessageText(text: String, color: Color, bold: Boolean = false) {
    Text(
        text = text,
        color = color,
        fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
        fontSize = 14.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun PasswordSettingsFormField(
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
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
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
fun ChangePasswordSettingsScreenPreview() {
    ChangePasswordSettingsScreen(
        onBack = {},
        onPasswordChanged = {},
        isLoading = false,
        errorMessage = null
    )
}
