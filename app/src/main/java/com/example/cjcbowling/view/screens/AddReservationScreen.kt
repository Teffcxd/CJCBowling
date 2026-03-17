package com.example.cjcbowling.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cjcbowling.model.Reservation
import com.example.cjcbowling.viewmodel.ReservationViewModel

@Composable
fun AddReservationScreen(viewModel: ReservationViewModel, onFinish: () -> Unit) {

    var client by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var lane by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    Column {

        TextField(value = client, onValueChange = { client = it }, label = { Text("Cliente") })
        TextField(value = phone, onValueChange = { phone = it }, label = { Text("Teléfono") })
        TextField(value = lane, onValueChange = { lane = it }, label = { Text("Pista") })
        TextField(value = date, onValueChange = { date = it }, label = { Text("Fecha") })
        TextField(value = time, onValueChange = { time = it }, label = { Text("Hora") })

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {

            if (lane.isNotEmpty()) {

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

                client = ""
                phone = ""
                lane = ""
                date = ""
                time = ""

                onFinish()
            }

        }) {
            Text("Guardar Reserva")
        }
    }
}