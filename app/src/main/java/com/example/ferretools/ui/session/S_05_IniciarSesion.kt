package com.example.ferretools.ui.session

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.example.ferretools.navigation.AppRoutes

private val PrimaryColor = Color(0xFF2E7D32)
private val TextColor = Color(0xFF1B5E20)
private val HintColor = Color(0xFFBDBDBD)
private val LinkColor = Color(0xFF1976D2)
private val GrayText = Color(0xFF757575)
private val DividerColor = Color(0xFFBDBDBD).copy(alpha = 0.5f)

@Composable
fun S_05_IniciarSesion(
    navController: NavController,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    // viewModel: IniciarSesionViewModel = viewModel() // Para uso futuro
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.length >= 6
    val isFormValid = isEmailValid && isPasswordValid

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Iniciar Sesión",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        LoginFormField(
            label = "Correo electrónico",
            value = email,
            onValueChange = { email = it },
            placeholder = "Correo",
            keyboardType = KeyboardType.Email,
            isError = email.isNotBlank() && !isEmailValid,
            errorText = if (email.isNotBlank() && !isEmailValid) "Correo inválido" else null
        )

        Spacer(modifier = Modifier.height(16.dp))

        LoginFormField(
            label = "Contraseña",
            value = password,
            onValueChange = { password = it },
            placeholder = "Contraseña",
            isPassword = true,
            showPassword = showPassword,
            onTogglePassword = { showPassword = !showPassword },
            keyboardType = KeyboardType.Password,
            isError = password.isNotBlank() && !isPasswordValid,
            errorText = if (password.isNotBlank() && !isPasswordValid) "Mínimo 6 caracteres" else null
        )

        Spacer(modifier = Modifier.height(24.dp))
        Divider(color = DividerColor, thickness = 1.dp)
        ForgotPasswordLink(onClick = { navController.navigate(AppRoutes.Auth.RECOVER_PASSWORD) })
        Divider(color = DividerColor, thickness = 1.dp)
        Spacer(modifier = Modifier.height(24.dp))

        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        LoginButton(isFormValid && !isLoading) {
            // Aquí puedes llamar a tu ViewModel o lógica de login
            // viewModel.login(email, password)
            // Por ahora, navega a la pantalla principal del admin (puedes cambiarlo según el rol)
            navController.navigate(AppRoutes.Admin.DASHBOARD)
        }

        Spacer(modifier = Modifier.height(16.dp))
        RegisterLink(onClick = { navController.navigate(AppRoutes.Auth.REGISTER_USER) })
    }
}

@Composable
fun LoginFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    showPassword: Boolean = false,
    onTogglePassword: (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    errorText: String? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = TextColor,
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = HintColor) },
            singleLine = true,
            isError = isError,
            visualTransformation = if (isPassword && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            trailingIcon = if (isPassword && onTogglePassword != null) {
                {
                    val icon = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = onTogglePassword) {
                        Icon(imageVector = icon, contentDescription = if (showPassword) "Ocultar contraseña" else "Mostrar contraseña")
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

@Composable
fun ForgotPasswordLink(onClick: () -> Unit) {
    Text(
        text = "¿Olvidaste tu contraseña?",
        color = LinkColor,
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.End,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        textDecoration = TextDecoration.Underline
    )
}

@Composable
fun LoginButton(enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(24.dp),
        elevation = ButtonDefaults.buttonElevation(4.dp)
    ) {
        Text(
            text = "INICIAR SESIÓN",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
fun RegisterLink(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¿No tienes cuenta? ",
            color = GrayText,
            fontSize = 15.sp
        )
        Text(
            text = "Registrarse",
            color = PrimaryColor,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = Modifier.clickable { onClick() },
            textDecoration = TextDecoration.Underline
        )
    }
}

@Preview(showBackground = true)
@Composable
fun S_05_IniciarSesionPreview() {
    val navController = rememberNavController()
    S_05_IniciarSesion(navController = navController)
}