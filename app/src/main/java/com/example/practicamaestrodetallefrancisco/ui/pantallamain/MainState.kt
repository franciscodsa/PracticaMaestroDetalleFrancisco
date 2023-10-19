package com.example.pracricaformulario.ui.pantallaMain

import com.example.practicamaestrodetallefrancisco.domain.modelo.FichaMascota

data class MainState(
    val fichaMascota: FichaMascota = FichaMascota("", "", "", "", 0, false, false, 0.0f),
    val mensaje: String? = null
)



