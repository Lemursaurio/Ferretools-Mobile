package com.example.ferretools.ui.components.seleccion_productos

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.ferretools.R

@Composable
fun ScanButton(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.escaner),
        contentDescription = "Escanear producto",
        modifier = modifier
    )
}