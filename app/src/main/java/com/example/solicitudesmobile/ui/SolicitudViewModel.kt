package com.example.solicitudesmobile.ui

import androidx.lifecycle.ViewModel
import com.example.solicitudesmobile.data.Solicitud
import com.example.solicitudesmobile.data.SolicitudRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Estado agrupado con la lista de solicitudes y los campos del formulario
data class FormularioUiState(
    val solicitudes: List<Solicitud> = emptyList(),
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
    val mensajeExito: String? = null,
    val mensajeQR: String? = null // <-- NUEVO: Aviso de lectura de QR
)

class SolicitudViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        FormularioUiState(solicitudes = SolicitudRepository.obtenerSolicitudes())
    )
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

    // Registra el código capturado y asigna el mensaje de confirmación
    fun registrarQRCaptado(codigo: String) {
        _uiState.update { state ->
            state.copy(
                mensajeQR = "¡Código QR captado con éxito! ($codigo)",
                ubicacion = codigo // Completa la ubicación con el dato del QR
            )
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
        val estadoActual = _uiState.value
        if (estadoActual.esValido) {
            val nuevaSolicitud = Solicitud(
                id = System.currentTimeMillis().toString(),
                titulo = estadoActual.titulo,
                descripcion = estadoActual.descripcion,
                categoria = estadoActual.categoria,
                fecha = "18/10/2026"
            )

            _uiState.update { state ->
                state.copy(
                    solicitudes = state.solicitudes + nuevaSolicitud,
                    titulo = "",
                    descripcion = "",
                    categoria = "",
                    prioridad = "Media",
                    ubicacion = "",
                    esValido = false,
                    mensajeExito = "¡Solicitud guardada con éxito!",
                    mensajeQR = null
                )
            }
        }
    }

    fun limpiarMensaje() {
        _uiState.update { it.copy(mensajeExito = null, mensajeQR = null) }
    }
}