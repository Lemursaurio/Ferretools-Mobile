package com.example.ferretools.ui.session

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.example.ferretools.navigation.AppRoutes

@Composable
fun S_06_RecuperarContrasena(
    navController: NavController,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    // viewModel: RecuperarContrasenaViewModel = viewModel() // Para uso futuro
) {
    var email by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var codeSent by remember { mutableStateOf(false) }
    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isCodeValid = code.length >= 4 // Puedes ajustar la longitud del código

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        // Botón de retroceso
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color(0xFF333333)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Spacer(modifier = Modifier.width(48.dp)) // Para equilibrar el espacio del botón
        }
        Text(
            text = "Recuperar contraseña",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Se le mandará un código de confirmación.",
            color = Color(0xFF666666),
            fontSize = 15.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        // Grupo de correo electrónico
        if (!codeSent) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "•",
                    color = Color(0xFF444444),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = "Ingrese su correo",
                    color = Color(0xFF444444),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Correo", color = Color(0xFF999999)) },
                singleLine = true,
                isError = email.isNotBlank() && !isEmailValid,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 20.dp)
            )
            if (email.isNotBlank() && !isEmailValid) {
                Text(
                    text = "Correo inválido",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Botón "Enviar código"
            TextButton(
                onClick = {
                    // Aquí puedes llamar a tu ViewModel o lógica para enviar el código
                    // viewModel.sendCode(email)
                    codeSent = true
                },
                enabled = isEmailValid && !isLoading,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "ENVIAR CÓDIGO",
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }

        if (codeSent) {
            Divider(
                color = Color(0xFFF5F5F5),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Grupo de código de verificación
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "•",
                    color = Color(0xFF444444),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = "Escriba el código",
                    color = Color(0xFF444444),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
            OutlinedTextField(
                value = code,
                onValueChange = { code = it },
                placeholder = { Text("Código", color = Color(0xFF999999)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 20.dp)
            )

            // Botón principal "Continuar"
            Button(
                onClick = {
                    // Aquí puedes llamar a tu ViewModel o lógica para continuar
                    // viewModel.verifyCode(code)
                    // Por ahora, navega a la pantalla de cambiar contraseña
                    navController.navigate(AppRoutes.Auth.CHANGE_PASSWORD)
                },
                enabled = isCodeValid && !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(4.dp)
            ) {
                Text(
                    text = "CONTINUAR",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun S_06_RecuperarContrasenaPreview() {
    val navController = rememberNavController()
    S_06_RecuperarContrasena(navController = navController)
}