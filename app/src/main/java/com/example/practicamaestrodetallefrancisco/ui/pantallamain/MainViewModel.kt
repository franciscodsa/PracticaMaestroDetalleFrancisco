package com.example.practicamaestrodetallefrancisco.ui.pantallamain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pracricaformulario.ui.pantallaMain.MainState
import com.example.practicamaestrodetallefrancisco.R
import com.example.practicamaestrodetallefrancisco.domain.modelo.FichaMascota
import com.example.practicamaestrodetallefrancisco.domain.usecases.AddFichaMascotaUseCase
import com.example.practicamaestrodetallefrancisco.domain.usecases.DeleteFichaMascota
import com.example.practicamaestrodetallefrancisco.domain.usecases.GetFichaMascotas
import com.example.practicamaestrodetallefrancisco.domain.usecases.UpdateFichaMascotaUseCase
import com.example.practicamaestrodetallefrancisco.utils.StringProvider


class MainViewModel(
    private val stringProvider: StringProvider,
    private val addFichaMascotaUseCase: AddFichaMascotaUseCase,
    private val getFichaMascotas: GetFichaMascotas,
    private val deleteFichaMascota: DeleteFichaMascota,
    private val updateFichaMascotaUseCase: UpdateFichaMascotaUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<MainState>()

    val uiState: LiveData<MainState> get() = _uiState

    private var fichaMascotas: List<FichaMascota> = emptyList()
    private var indiceActual = -1 // Inicialmente no hay ficha actual

    init {
        cargarFichaMascotas()
    }

    private fun cargarFichaMascotas() {
        fichaMascotas = getFichaMascotas()
    }

    fun addFichaMascota(fichaMascota: FichaMascota) {

        if (fichaMascota.propietario.isBlank() || fichaMascota.nombreMascota.isBlank() || fichaMascota.email.isBlank() || fichaMascota.telefono.isBlank()) {
            _uiState.value = MainState(mensaje = Constantes.DEDES_RELLENAR_TODOS_LOS_CAMPOS)
        } else {
            if (!addFichaMascotaUseCase(fichaMascota)) {
                _uiState.value = MainState(mensaje = stringProvider.getString(R.string.name))
                _uiState.value = _uiState.value?.copy(mensaje = Constantes.MENSAJE)
            } else {
                cargarFichaMascotas() // Recargar la lista de fichas
                _uiState.value = _uiState.value?.copy(mensaje = Constantes.FICHA_ANADIDA)
            }
        }

    }

    fun mostrarSiguienteFicha() {
        if (fichaMascotas.isNotEmpty() && indiceActual < fichaMascotas.size - 1) {
            indiceActual++
        }
        val fichaActual = fichaMascotas.getOrElse(indiceActual) { fichaMascotas.last() }
        _uiState.value = MainState(fichaMascota = fichaActual, mensaje = null)
    }

    fun mostrarFichaAnterior() {
        if (fichaMascotas.isNotEmpty() && indiceActual > 0) {
            indiceActual--
        }
        val fichaActual = fichaMascotas.getOrElse(indiceActual) { fichaMascotas.first() }
        _uiState.value = MainState(fichaMascota = fichaActual, mensaje = null)
    }

    fun eliminarFichaActual() {
        if (fichaMascotas.isNotEmpty() && indiceActual >= 0 && indiceActual < fichaMascotas.size) {
            val fichaActual = fichaMascotas[indiceActual]
            deleteFichaMascota(fichaActual)
            cargarFichaMascotas() // Recargar la lista de fichas después de eliminar
            indiceActual = -1 // No hay ficha actual después de eliminar
            _uiState.value = MainState(mensaje = Constantes.FICHA_ELIMINADA)
        } else {
            _uiState.value = MainState(mensaje = Constantes.NO_HAY_FICHA_QUE_ELIMINAR)
        }
    }

    fun modificarFichaActual(fichaMascotaModificada: FichaMascota) {
        if (fichaMascotas.isNotEmpty() && indiceActual >= 0 && indiceActual < fichaMascotas.size) {
            updateFichaMascotaUseCase(fichaMascotas[indiceActual], fichaMascotaModificada)
            _uiState.value = _uiState.value?.copy(
                mensaje = Constantes.FICHA_MODIFICADA,
                fichaMascota = fichaMascotas[indiceActual]
            )
        } else {
            _uiState.value = MainState(mensaje = Constantes.NO_HAY_FICHA_QUE_MODIFICAR)
        }
    }

    fun mensajeMostrado() {
        _uiState.value = _uiState.value?.copy(mensaje = null)
    }


}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class MainViewModelFactory(
    private val stringProvider: StringProvider,
    private val addFichaMascotaUseCase: AddFichaMascotaUseCase,
    private val getFichaMascotas: GetFichaMascotas,
    private val deleteFichaMascota: DeleteFichaMascota,
    private val updateFichaMascotaUseCase: UpdateFichaMascotaUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return MainViewModel(
                stringProvider,
                addFichaMascotaUseCase,
                getFichaMascotas,
                deleteFichaMascota,
                updateFichaMascotaUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}