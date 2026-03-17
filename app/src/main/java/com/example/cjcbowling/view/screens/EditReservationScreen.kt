package com.example.cjcbowling.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cjcbowling.model.Reservation
import com.example.cjcbowling.viewmodel.ReservationViewModel

@Composable
fun EditReservationScreen(
    viewModel: ReservationViewModel,
    reservation: Reservation,
    onFinish: () -> Unit
) {

    var client by remember { mutableStateOf(reservation.clientName) }
    var phone by remember { mutableStateOf(reservation.phone) }
    var lane by remember { mutableStateOf(reservation.lane.toString()) }
    var date by remember { mutableStateOf(reservation.date) }
    var time by remember { mutableStateOf(reservation.time) }
    var status by remember { mutableStateOf(reservation.status) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text("Editar Reserva", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(10.dp))

        TextField(value = client, onValueChange = { client = it }, label = { Text("Cliente") })
        TextField(value = phone, onValueChange = { phone = it }, label = { Text("Teléfono") })
        TextField(value = lane, onValueChange = { lane = it }, label = { Text("Pista") })
        TextField(value = date, onValueChange = { date = it }, label = { Text("Fecha") })
        TextField(value = time, onValueChange = { time = it }, label = { Text("Hora") })

        Spacer(modifier = Modifier.height(10.dp))

        Text("Estado")

        Row {

            Button(onClick = { status = "Activa" }) {
                Text("Activa")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = { status = "Finalizada" }) {
                Text("Finalizada")
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {

            if (lane.isNotEmpty()) {

                val updated = reservation.copy(
                    clientName = client,
                    phone = phone,
                    lane = lane.toInt(),
                    date = date,
                    time = time,
                    status = status
                )

                viewModel.updateReservation(updated)

                onFinish()
            }

        }) {
            Text("Actualizar Reserva")
        }
    }
}