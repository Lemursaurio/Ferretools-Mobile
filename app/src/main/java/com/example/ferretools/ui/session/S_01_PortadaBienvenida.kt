package com.example.ferretools.ui.session

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ferretools.R
import com.example.ferretools.theme.FerretoolsTheme

@Composable
fun BienvenidaScreen(
    onCrearCuentaClick: () -> Unit,
    onIniciarSesionClick: () -> Unit
) {
    val green = Color(0xFF2E7D32)
    val darkGreen = Color(0xFF1B5E20)
    val white = Color.White
    val cornerRadius = 24.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.fondo_almacen),
            contentDescription = "Imagen de almacén",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        )

        // Panel central
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .width(320.dp)
                .height(360.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(green)
                .shadow(8.dp, RoundedCornerShape(32.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Gestiona tu Ferretería",
                    color = white,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                )

                BotonAccion(
                    texto = "CREAR CUENTA",
                    backgroundColor = white,
                    contentColor = green,
                    onClick = onCrearCuentaClick
                )

                Spacer(modifier = Modifier.height(16.dp))

                BotonAccion(
                    texto = "INICIAR SESIÓN",
                    backgroundColor = darkGreen,
                    contentColor = white,
                    onClick = onIniciarSesionClick
                )
            }
        }
    }
}

@Composable
fun BotonAccion(
    texto: String,
    backgroundColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(24.dp),
        elevation = ButtonDefaults.buttonElevation(4.dp)
    ) {
        Text(
            text = texto,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}


@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun BienvenidaScreenPreview() {
    // Define un tema para la vista previa (opcional pero recomendado)
    FerretoolsTheme { // Reemplaza "YourAppTheme" con el nombre de tu tema de Material3
        BienvenidaScreen(
            onCrearCuentaClick = { /* Acción al hacer clic en Crear Cuenta */ },
            onIniciarSesionClick = { /* Acción al hacer clic en Iniciar Sesión */ }
        )
    }
}