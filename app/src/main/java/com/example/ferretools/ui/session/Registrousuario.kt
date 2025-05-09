package com.example.ferretools.ui.session

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
/*
@Composable
fun RegistroUsuarioScreen(
    viewModel: RegistroViewModel = viewModel(),
    onRegistroExitoso: () -> Unit,
    onRegistrarNegocio: () -> Unit
) {
    val rol = viewModel.rolSeleccionado
    var nombres by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }

    var errores by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Crear Cuenta",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )

        OutlinedTextField(
            value = nombres,
            onValueChange = { nombres = it },
            label = { Text("Nombres completos") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = confirmarContrasena,
            onValueChange = { confirmarContrasena = it },
            label = { Text("Confirmar contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        // Separador
        Divider(color = Color.Gray.copy(alpha = 0.4f), thickness = 1.dp)

        // Errores
        if (errores.isNotEmpty()) {
            Text(
                text = errores,
                color = Color.Red,
                fontSize = 14.sp
            )
        }

        Button(
            onClick = {
                errores = validarFormulario(correo, contrasena, confirmarContrasena, nombres, telefono)
                if (errores.isEmpty()) {
                    if (rol == "Administrador") {
                        onRegistrarNegocio()
                    } else {
                        onRegistroExitoso()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("REGISTRARSE")
        }
    }
}

fun validarFormulario(
    correo: String,
    contrasena: String,
    confirmar: String,
    nombres: String,
    telefono: String
): String {
    if (nombres.isBlank() || correo.isBlank() || telefono.isBlank() || contrasena.isBlank() || confirmar.isBlank()) {
        return "Todos los campos son obligatorios."
    }
    if (!correo.contains("@") || !correo.contains(".")) {
        return "Correo electrónico inválido."
    }
    if (contrasena.length < 6) {
        return "La contraseña debe tener al menos 6 caracteres."
    }
    if (contrasena != confirmar) {
        return "Las contraseñas no coinciden."
    }
    return ""
}
*/
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person

import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ferretools.R

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

    // Validaciones
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
            //Spacer(modifier = Modifier.width(8.dp))

            //Spacer(modifier = Modifier.width(48.dp)) // Para equilibrar el espacio del botón
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Crear Cuenta",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        // Imagen de perfil (opcional)
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E0E0))
                .clickable { /* TODO: Seleccionar imagen */ },
            contentAlignment = Alignment.Center
        ) {
            if (imageUri != null) {
                // Aquí puedes cargar la imagen seleccionada
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // Cambia por tu lógica de carga de imagen
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
        Spacer(modifier = Modifier.height(24.dp))

        // Campos del formulario
        FormField(
            label = "Nombres completos",
            value = name,
            onValueChange = { name = it },
            placeholder = "Ingrese sus nombres"
        )
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
        FormField(
            label = "Teléfono",
            value = phone,
            onValueChange = { phone = it },
            placeholder = "Teléfono"
        )
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
                onRegister(name, email, phone, password, imageUri)
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
                text = "REGISTRARSE",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
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
                    val icon = if (showPassword) Icons.Default.Person else Icons.Default.Person // Cambia por iconos de ojo si los tienes
                    IconButton(onClick = onTogglePassword) {
                        Icon(imageVector = icon, contentDescription = "Mostrar/Ocultar contraseña")
                    }
                }
            } else null,
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
fun RegisterUserScreenPreview() {
    RegisterUserScreen(
        onBack = {},
        onRegister = { _, _, _, _, _ -> },
        isLoading = false,
        errorMessage = null
    )
}