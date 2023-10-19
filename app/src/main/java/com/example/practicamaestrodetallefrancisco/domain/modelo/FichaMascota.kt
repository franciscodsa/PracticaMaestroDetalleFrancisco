package com.example.practicamaestrodetallefrancisco.domain.modelo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FichaMascota(
    val propietario: String = "",
    val email: String = "",
    val telefono: String = "",
    val nombreMascota: String = "",
    val especie: Int,
    val esterilizado: Boolean = false,
    val vacunado: Boolean = false,
    val comportamiento: Float = 0.0f
): Parcelable
