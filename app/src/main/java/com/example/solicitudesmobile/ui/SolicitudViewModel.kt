package com.example.solicitudesmobile.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Estado visual de la pantalla de formulario
data class FormularioUiState(
    val titulo: String = "",
    val tituloError: String? = null,
    val descripcion: String = "",
    val descripcionError: String? = null,
    val categoria: String = "",
    val categoriaError: String? = null,
    val prioridad: String = "Media",
    val ubicacion: String = "",
    val ubicacionError: String? = null,
    val esValido: Boolean = false,
    val mensajeExito: String? = null
)

class SolicitudViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FormularioUiState())
    val uiState: StateFlow<FormularioUiState> = _uiState.asStateFlow()

    fun onTituloChanged(nuevoTitulo: String) {
        _uiState.update { state ->
            val error = if (nuevoTitulo.isBlank()) "El título es obligatorio" else null
            state.copy(titulo = nuevoTitulo, tituloError = error)
        }
        validarFormulario()
    }

    fun onDescripcionChanged(nuevaDescripcion: String) {
        _uiState.update { state ->
            val error = if (nuevaDescripcion.length < 5) "La descripción debe tener al menos 5 caracteres" else null
            state.copy(descripcion = nuevaDescripcion, descripcionError = error)
        }
        validarFormulario()
    }

    fun onCategoriaChanged(nuevaCategoria: String) {
        _uiState.update { state ->
            val error = if (nuevaCategoria.isBlank()) "La categoría es requerida" else null
            state.copy(categoria = nuevaCategoria, categoriaError = error)
        }
        validarFormulario()
    }

    fun onPrioridadChanged(nuevaPrioridad: String) {
        _uiState.update { it.copy(prioridad = nuevaPrioridad) }
    }

    fun onUbicacionChanged(nuevaUbicacion: String) {
        _uiState.update { state ->
            val error = if (nuevaUbicacion.isBlank()) "La ubicación es obligatoria" else null
            state.copy(ubicacion = nuevaUbicacion, ubicacionError = error)
        }
        validarFormulario()
    }

    private fun validarFormulario() {
        val s = _uiState.value
        val esValido = s.titulo.isNotBlank() &&
                s.descripcion.length >= 5 &&
                s.categoria.isNotBlank() &&
                s.ubicacion.isNotBlank() &&
                s.tituloError == null &&
                s.descripcionError == null &&
                s.categoriaError == null &&
                s.ubicacionError == null

        _uiState.update { it.copy(esValido = esValido) }
    }

    fun guardarSolicitud() {
        if (_uiState.value.esValido) {
            _uiState.update { it.copy(mensajeExito = "¡Solicitud guardada con éxito!") }
        }
    }

    fun limpiarMensaje() {
        _uiState.update { it.copy(mensajeExito = null) }
    }
}