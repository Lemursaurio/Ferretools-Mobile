package com.example.ferretools.model.registro

import android.net.Uri
import com.example.ferretools.model.database.Usuario

data class RegistroNegocioUiState(
    var businessName: String = "",
    var businessType: String = "",
    var address: String = "",
    var ruc: String = "",
    var ownerId: String = "",
    var logoUri: Uri? = null,
    var isFormValid: Boolean = false
)