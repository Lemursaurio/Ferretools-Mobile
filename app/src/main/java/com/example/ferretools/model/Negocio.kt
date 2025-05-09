package com.example.ferretools.model

import com.google.firebase.firestore.DocumentReference

data class Negocio(
    val nombre: String,
    val tipo: String,
    val gerente_id: DocumentReference,
)
