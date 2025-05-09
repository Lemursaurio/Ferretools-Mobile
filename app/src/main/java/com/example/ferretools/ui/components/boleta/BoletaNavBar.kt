package com.example.ferretools.ui.components.boleta

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ferretools.R

@Composable
fun BoletaNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(R.drawable.pdf),
                contentDescription = "PDF",
                modifier = Modifier.size(80.dp)
            )
            Text("PDF", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(R.drawable.excel),
                contentDescription = "EXCEL",
                modifier = Modifier.size(80.dp)
            )
            Text("EXCEL", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(R.drawable.share),
                contentDescription = "ENVIAR",
                modifier = Modifier.size(80.dp)
            )
            Text("ENVIAR", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
    }
}