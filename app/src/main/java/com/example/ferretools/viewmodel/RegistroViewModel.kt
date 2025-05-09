package com.example.ferretools.viewmodel
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.getValue // Para acceso directo al valor
import androidx.compose.runtime.setValue // Para modificar el valor


class RegistroViewModel : ViewModel() {
    var rolSeleccionado by mutableStateOf<String?>(null)
        private set

    fun seleccionarRol(rol: String) {
        rolSeleccionado = rol
    }
}