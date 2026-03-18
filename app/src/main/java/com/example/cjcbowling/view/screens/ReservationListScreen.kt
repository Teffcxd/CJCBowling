package com.example.cjcbowling.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cjcbowling.viewmodel.ReservationViewModel
import com.example.cjcbowling.view.components.ReservationItem
import com.example.cjcbowling.model.Reservation

@Composable
fun ReservationListScreen(
    viewModel: ReservationViewModel,
    onEdit: (Reservation) -> Unit
) {

    var search by remember { mutableStateOf("") }

    val reservations = viewModel.reservations

    // 🔎 FILTRO LOCAL (MEJOR QUE EN VIEWMODEL)
    val filteredReservations = reservations.filter {
        it.clientName.contains(search, ignoreCase = true) ||
                it.phone.contains(search) ||
                it.date.contains(search) ||
                it.lane.toString().contains(search)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2C2C54),
                        Color(0xFF5A189A),
                        Color(0xFF9D4EDD)
                    )
                )
            )
            .padding(16.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // 🔍 BUSCADOR BONITO
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    label = { Text("🔍 Buscar reserva") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    singleLine = true
                )
            }

            // ⚠ MENSAJE SI NO HAY RESULTADOS
            if (filteredReservations.isEmpty()) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Text(
                        text = "No se encontraron reservas",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            // 📋 LISTA
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredReservations) { reservation ->

                    ReservationItem(
                        reservation = reservation,
                        onDelete = { viewModel.deleteReservation(reservation) },
                        onEdit = { onEdit(reservation) }
                    )
                }
            }
        }
    }
}