package com.example.ferretools.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex

@Composable
fun LoadingDialog(
    message: String = "Cargando...",
    show: Boolean
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { /* No permitir cerrar */ },
            confirmButton = {},
            title = null,
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = message)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingDialogPreview() {
    LoadingDialog(show = true)
}

@Composable
fun LoadingOverlay(
    show: Boolean,
    message: String = "Cargando..."
) {
    if (show) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
                .zIndex(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(32.dp)
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = message, color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingOverlayPreview() {
    LoadingOverlay(show = true)
}