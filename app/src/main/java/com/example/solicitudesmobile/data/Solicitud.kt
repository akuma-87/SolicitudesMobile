package com.example.solicitudesmobile.data

data class Solicitud(val id: String,
                     val titulo: String,
                     val descripcion: String,
                     val categoria: String,
                     val fecha: String,
                     val estado: String = "Pendiente",
                     val imagenUri: String? = null)
