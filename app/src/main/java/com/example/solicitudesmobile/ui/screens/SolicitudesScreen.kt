package com.example.solicitudesmobile.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.solicitudesmobile.data.Solicitud
import com.example.solicitudesmobile.data.SolicitudRepository
import com.example.solicitudesmobile.ui.theme.SolicitudesMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolicitudesScreen() {
    val listaSolicitudes = SolicitudRepository.obtenerSolicitudes()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Gestión de Solicitudes") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listaSolicitudes) { solicitud ->
                ItemSolicitud(solicitud)
            }
        }
    }
}

@Composable
fun ItemSolicitud(solicitud: Solicitud) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = solicitud.titulo, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = solicitud.descripcion, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Categoría: ${solicitud.categoria}", style = MaterialTheme.typography.labelSmall)
                Text(text = solicitud.fecha, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SolicitudesScreenPreview() {
    SolicitudesMobileTheme {
        SolicitudesScreen()
    }
}