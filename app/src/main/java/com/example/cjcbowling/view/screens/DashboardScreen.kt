package com.example.cjcbowling.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cjcbowling.viewmodel.ReservationViewModel
import com.example.cjcbowling.view.components.ReservationItem

@Composable
fun DashboardScreen(viewModel: ReservationViewModel) {

    var showForm by remember { mutableStateOf(false) }

    val reservations = viewModel.reservations

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "🎳 CJC - Bowling 🎳  ",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Total reservas: ${reservations.size}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { showForm = !showForm },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (showForm) "Ocultar formulario" else "Nueva Reserva")
        }

        Spacer(modifier = Modifier.height(16.dp))


        if (showForm) {
            AddReservationScreen(viewModel)
            Spacer(modifier = Modifier.height(16.dp))
        }


        LazyColumn {

            items(reservations) { reservation ->

                ReservationItem(
                    reservation = reservation,
                    onDelete = { viewModel.deleteReservation(reservation) },
                    onEdit = {}
                )
            }
        }
    }
}