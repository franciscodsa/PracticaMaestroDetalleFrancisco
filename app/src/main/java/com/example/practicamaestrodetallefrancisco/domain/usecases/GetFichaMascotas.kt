package com.example.practicamaestrodetallefrancisco.domain.usecases

import com.example.practicamaestrodetallefrancisco.data.Repository

class GetFichaMascotas {
    operator fun invoke() = Repository.getFichaMascotas()
}