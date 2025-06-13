package com.example.ferretools.model.database

import com.google.firebase.firestore.DocumentReference

data class ItemUnitario(
    val cantidad: Int,
    val producto_id: DocumentReference,
)
