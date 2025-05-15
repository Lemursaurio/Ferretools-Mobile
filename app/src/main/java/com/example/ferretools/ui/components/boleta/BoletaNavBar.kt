package com.example.ferretools.ui.components.boleta

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ferretools.R
import com.example.ferretools.navigation.AppRoutes

@Composable
fun BoletaNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    onPdfClick: (() -> Unit)? = null,
    onExcelClick: (() -> Unit)? = null,
    onShareClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { onPdfClick?.invoke() }
        ) {
            Image(
                painterResource(R.drawable.pdf),
                contentDescription = "PDF",
                modifier = Modifier.size(80.dp)
            )
            Text("PDF", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { onExcelClick?.invoke() }
        ) {
            Image(
                painterResource(R.drawable.excel),
                contentDescription = "EXCEL",
                modifier = Modifier.size(80.dp)
            )
            Text("EXCEL", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable {
                onShareClick?.invoke()
                    ?: navController.navigate(AppRoutes.Inventory.SHARE_REPORT)
            }
        ) {
            Image(
                painterResource(R.drawable.share),
                contentDescription = "ENVIAR",
                modifier = Modifier.size(80.dp)
            )
            Text("ENVIAR", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
    }
}