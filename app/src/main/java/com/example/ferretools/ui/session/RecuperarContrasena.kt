package com.example.ferretools.ui.session

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PasswordRecoveryScreen(
    onBack: () -> Unit,
    onSendCode: (String) -> Unit,
    onContinue: (String) -> Unit,
    isLoading: Boolean = false,
    errorMessage: String? = null
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
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color(0xFF333333)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            //
            Spacer(modifier = Modifier.width(48.dp)) // Para equilibrar el espacio del botón
        }
        Text(
            text = "Recuperar contraseña",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            //modifier = Modifier.weight(1f),
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
                    onSendCode(email)
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
                onClick = { onContinue(code) },
                enabled = isCodeValid && !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(top = 24.dp),
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
                    fontSize = 16.sp
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
fun PasswordRecoveryScreenPreview() {
    PasswordRecoveryScreen(
        onBack = {},
        onSendCode = {},
        onContinue = {},
        isLoading = false,
        errorMessage = null
    )
}