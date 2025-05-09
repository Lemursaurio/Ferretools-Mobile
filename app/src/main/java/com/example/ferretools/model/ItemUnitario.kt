package com.example.ferretools.model

import com.google.firebase.firestore.DocumentReference

data class ItemUnitario(
    val cantidad: Int,
    val producto_id: DocumentReference,
)
