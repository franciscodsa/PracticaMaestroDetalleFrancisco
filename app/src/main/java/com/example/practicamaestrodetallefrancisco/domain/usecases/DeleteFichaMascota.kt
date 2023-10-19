package com.example.practicamaestrodetallefrancisco.domain.usecases

import com.example.practicamaestrodetallefrancisco.data.Repository
import com.example.practicamaestrodetallefrancisco.domain.modelo.FichaMascota

class DeleteFichaMascota {
    operator fun invoke(fichaMascota: FichaMascota) = Repository.deleteFichaMascota(fichaMascota)
}