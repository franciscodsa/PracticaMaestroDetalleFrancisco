package com.example.practicamaestrodetallefrancisco.domain.usecases

import com.example.practicamaestrodetallefrancisco.data.Repository
import com.example.practicamaestrodetallefrancisco.domain.modelo.FichaMascota

class UpdateFichaMascotaUseCase {
    operator fun invoke(fichaMascota: FichaMascota, fichaMascotaUpdated: FichaMascota) =
        Repository.updateFichaMascota(fichaMascota, fichaMascotaUpdated)
}