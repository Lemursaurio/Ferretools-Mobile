package com.example.ferretools.ui.session

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ferretools.R
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RegisterUserScreen(
    onBack: () -> Unit,
    onRegister: (name: String, email: String, phone: String, password: String, imageUri: String?) -> Unit,
    isLoading: Boolean = false,
    errorMessage: String? = null
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<String?>(null) }

    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.length >= 6
    val doPasswordsMatch = password == confirmPassword
    val areFieldsFilled = name.isNotBlank() && email.isNotBlank() && phone.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()
    val isFormValid = isEmailValid && isPasswordValid && doPasswordsMatch && areFieldsFilled

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(onBack)

        Spacer(modifier = Modifier.height(16.dp))

        Title(text = "Crear Cuenta")

        Spacer(modifier = Modifier.height(24.dp))

        ProfileImage(imageUri)

        Spacer(modifier = Modifier.height(24.dp))

        FormField(label = "Nombres completos", value = name, onValueChange = { name = it }, placeholder = "Ingrese sus nombres")

        Spacer(modifier = Modifier.height(16.dp))

        FormField(
            label = "Correo electrónico",
            value = email,
            onValueChange = { email = it },
            placeholder = "Correo",
            isError = email.isNotBlank() && !isEmailValid,
            errorText = if (email.isNotBlank() && !isEmailValid) "Correo inválido" else null
        )

        Spacer(modifier = Modifier.height(16.dp))

        FormField(label = "Teléfono", value = phone, onValueChange = { phone = it }, placeholder = "Teléfono")

        Spacer(modifier = Modifier.height(16.dp))

        FormField(
            label = "Contraseña",
            value = password,
            onValueChange = { password = it },
            placeholder = "Contraseña",
            isPassword = true,
            showPassword = showPassword,
            onTogglePassword = { showPassword = !showPassword },
            isError = password.isNotBlank() && !isPasswordValid,
            errorText = if (password.isNotBlank() && !isPasswordValid) "Mínimo 6 caracteres" else null
        )

        Spacer(modifier = Modifier.height(16.dp))

        FormField(
            label = "Confirmar contraseña",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = "Repite la contraseña",
            isPassword = true,
            showPassword = showConfirmPassword,
            onTogglePassword = { showConfirmPassword = !showConfirmPassword },
            isError = confirmPassword.isNotBlank() && !doPasswordsMatch,
            errorText = if (confirmPassword.isNotBlank() && !doPasswordsMatch) "Las contraseñas no coinciden" else null
        )

        Spacer(modifier = Modifier.height(24.dp))

        Divider(color = Color(0xFFBDBDBD).copy(alpha = 0.5f), thickness = 1.dp)

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
            onClick = { onRegister(name, email, phone, password, imageUri) },
            enabled = isFormValid && !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D32),
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(4.dp)
        ) {
            Text("REGISTRARSE", fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
    }
}

@Composable
private fun Title(text: String) {
    Text(
        text = text,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1B5E20),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun ProfileImage(imageUri: String?) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .clip(CircleShape)
            .background(Color(0xFFE0E0E0))
            .clickable { /* TODO: Selector de imagen */ },
        contentAlignment = Alignment.Center
    ) {
        if (imageUri != null) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Imagen de perfil",
                modifier = Modifier.size(90.dp)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Agregar imagen de perfil",
                tint = Color(0xFF757575),
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Composable
fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    showPassword: Boolean = false,
    onTogglePassword: (() -> Unit)? = null,
    isError: Boolean = false,
    errorText: String? = null
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
            isError = isError,
            visualTransformation = if (isPassword && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = if (isPassword && onTogglePassword != null) {
                {
                    val icon = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff // Cambia por iconos de ojo si los tienes
                    IconButton(onClick = onTogglePassword) {
                        Icon(imageVector = icon, contentDescription = "Mostrar/Ocultar contraseña")
                    }
                }
            } else null,
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


@Preview(showBackground = true)
@Composable
fun RegisterUserScreenPreview() {
    RegisterUserScreen(
        onBack = {},
        onRegister = { _, _, _, _, _ -> },
        isLoading = false,
        errorMessage = null
    )
}