package com.example.cjcbowling.view.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import model.Reservation
import com.example.cjcbowling.viewmodel.ReservationViewModel

@Composable
fun AddReservationScreen(viewModel: ReservationViewModel) {

    var client by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var lane by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    Button(onClick = {

        val reservation = Reservation(
            id = System.currentTimeMillis().toInt(),
            clientName = client,
            phone = phone,
            lane = lane.toInt(),
            date = date,
            time = time,
            status = "Activa"
        )

        viewModel.addReservation(reservation)

    }) {

        Text("Guardar Reserva")
    }
}