package com.example.ferretools.model.database

import android.net.Uri
import com.google.firebase.firestore.DocumentReference

data class Negocio(
    val nombre: String,
    val tipo: String,
    val direccion: String,
    val ruc: String,
    val logoUri: Uri? = null,
    val gerenteId: String,
)
