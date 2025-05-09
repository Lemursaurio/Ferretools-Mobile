package com.example.ferretools.ui.session

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.ferretools.ui.theme.FerreToolsTheme
import com.example.ferretools.R

/*@Composable
fun BienvenidaScreen(
    onCrearCuentaClick: () -> Unit,
    onIniciarSesionClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo

        Image(
            painter = painterResource(id = R.drawable.fondo_almacen), // Coloca tu imagen en res/drawable
            contentDescription = "Imagen de almacén",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight() // Mitad superior
                .align(Alignment.TopCenter)
        )

        // Panel Verde con elementos centrados
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .align(Alignment.Center)
                .background(color = Color(0xFF388E3C), shape = RoundedCornerShape(24.dp))
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Gestiona tu Ferretería",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = onCrearCuentaClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = "CREAR CUENTA",
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onIniciarSesionClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = "INICIAR SESIÓN",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}*/

@Composable
fun BienvenidaScreen(
    onCrearCuentaClick: () -> Unit,
    onIniciarSesionClick: () -> Unit
) {
    // Colores principales
    val green = Color(0xFF2E7D32)
    val darkGreen = Color(0xFF1B5E20)
    val white = Color.White

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Imagen superior (puedes cambiar por tu imagen de almacén)
        Image(
            painter = painterResource(id = R.drawable.fondo_almacen), // Cambia por tu imagen
            contentDescription = "Imagen de almacén",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .align(Alignment.TopCenter)
        )

        // Panel verde superpuesto
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

                // Botón CREAR CUENTA
                Button(
                    onClick = onCrearCuentaClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = white,
                        contentColor = green
                    ),
                    shape = RoundedCornerShape(24.dp),
                    elevation = ButtonDefaults.buttonElevation(4.dp)
                ) {
                    Text(
                        text = "CREAR CUENTA",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                // Botón INICIAR SESIÓN
                Button(
                    onClick = onIniciarSesionClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = darkGreen,
                        contentColor = white
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
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun BienvenidaScreenPreview() {
    // Define un tema para la vista previa (opcional pero recomendado)
    FerreToolsTheme { // Reemplaza "YourAppTheme" con el nombre de tu tema de Material3
        BienvenidaScreen(
            onCrearCuentaClick = { /* Acción al hacer clic en Crear Cuenta */ },
            onIniciarSesionClick = { /* Acción al hacer clic en Iniciar Sesión */ }
        )
    }
}