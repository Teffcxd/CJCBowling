
package com.example.cjcbowling.view.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import model.Reservation
import com.example.cjcbowling.viewmodel.ReservationViewModel

@Composable
fun EditReservationScreen(
    viewModel: ReservationViewModel,
    reservation: Reservation
) {

    var name by remember { mutableStateOf(reservation.clientName) }

    TextField(value = name, onValueChange = { name = it })

    Button(onClick = {

        val updated = reservation.copy(clientName = name)

        viewModel.updateReservation(updated)

    }) {

        Text("Actualizar")
    }
}