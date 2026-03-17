package com.example.cjcbowling.view.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.cjcbowling.model.Reservation

@Composable
fun ReservationItem(
    reservation: Reservation,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {

    Card {

        Text("Cliente: ${reservation.clientName}")
        Text("Fecha: ${reservation.date}")
        Text("Hora: ${reservation.time}")
        Text("Pista: ${reservation.lane}")
        Text("Estado: ${reservation.status}")

        Button(onClick = onEdit) {
            Text("Editar")
        }

        Button(onClick = onDelete) {
            Text("Eliminar")
        }
    }
}