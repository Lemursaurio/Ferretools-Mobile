package com.example.ferretools.ui.session

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ferretools.R
import com.example.ferretools.theme.FerretoolsTheme
import com.example.ferretools.navigation.AppRoutes

@Composable
fun S_01_PortadaBienvenida(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // Colores definidos como constantes
    val green = Color(0xFF2E7D32)
    val darkGreen = Color(0xFF1B5E20)
    val white = Color.White
    val cornerRadius = 24.dp

    Box(
        modifier = modifier
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

                // Botón Crear Cuenta
                Button(
                    onClick = {
                        navController.navigate(AppRoutes.Auth.REGISTER_USER) {
                            // Opciones de navegación
                            popUpTo(AppRoutes.Auth.WELCOME) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
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

                Spacer(modifier = Modifier.height(16.dp))

                // Botón Iniciar Sesión
                Button(
                    onClick = {
                        navController.navigate(AppRoutes.Auth.LOGIN) {
                            popUpTo(AppRoutes.Auth.WELCOME) { inclusive = true }
                        }
                    },
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

// Preview para

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun S_01_PortadaBienvenidaPreview() {
    FerretoolsTheme {
        val navController = rememberNavController()
        S_01_PortadaBienvenida(navController = navController)
    }
}