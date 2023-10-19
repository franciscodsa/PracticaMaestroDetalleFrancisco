package com.example.practicamaestrodetallefrancisco.data

import com.example.practicamaestrodetallefrancisco.R
import com.example.practicamaestrodetallefrancisco.domain.modelo.FichaMascota

object Repository {
    private val fichaMascotas = mutableListOf<FichaMascota>()

    init {
        fichaMascotas.add(
            FichaMascota(
                "Juan",
                "juan@mail.com",
                "87646413",
                "Max",
                R.id.radioPerro,
                false,
                true,
                5f
            )
        )
        fichaMascotas.add(
            FichaMascota(
                "Pepe",
                "pepe@mail.com",
                "648325432",
                "Luna",
                R.id.radioGato,
                false,
                false,
                2f
            )
        )
        fichaMascotas.add(
            FichaMascota(
                "Alejandro",
                "ale@mail.com",
                "65432101",
                "Simba",
                R.id.radioGato,
                true,
                true,
                8.5f
            )
        )
    }

    fun addFichaMascota(fichaMascota: FichaMascota) = fichaMascotas.add(fichaMascota)


    fun getFichaMascotas(): List<FichaMascota> {
        return fichaMascotas
    }

    fun deleteFichaMascota(fichaMascota: FichaMascota) = fichaMascotas.remove(fichaMascota)

    fun updateFichaMascota(fichaMascota: FichaMascota, fichaMascotaUpdated: FichaMascota) {
        val indice = fichaMascotas.indexOf(fichaMascota)
        fichaMascotas[indice] = fichaMascotaUpdated
    }
}
