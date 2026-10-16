package com.example.solicitudesmobile.data

import com.example.solicitudesmobile.data.Solicitud

object SolicitudRepository {
    fun obtenerSolicitudes(): List<Solicitud> {
        return listOf(
            Solicitud("1", "Mantenimiento PC", "Falla en inicio de sistema", "Soporte", "15/10/2026"),
            Solicitud("2", "Revisión de Red", "Sin conexión en oficina 3", "Redes", "16/10/2026")
        )
    }
}