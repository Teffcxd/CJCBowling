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

    var error by remember { mutableStateOf("") }

    var dateError by remember { mutableStateOf(false) }
    var timeError by remember { mutableStateOf(false) }
    var clientError by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("Editar Reserva", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(10.dp))


        TextField(
            value = client,
            onValueChange = {
                client = it
                clientError = it.isEmpty() || it.any { char -> char.isDigit() }
            },
            label = { Text("Cliente") },
            isError = clientError
        )

        if (clientError) {
            Text("Nombre inválido", color = MaterialTheme.colorScheme.error)
        }

        TextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Teléfono") }
        )


        TextField(
            value = lane,
            onValueChange = { lane = it },
            label = { Text("Pista") }
        )


        TextField(
            value = date,
            onValueChange = {
                date = it
                dateError = !isValidDate(it) && it.isNotEmpty()
            },
            label = { Text("Fecha (dd/mm/yyyy)") },
            isError = dateError
        )

        if (dateError) {
            Text("Fecha inválida", color = MaterialTheme.colorScheme.error)
        }


        TextField(
            value = time,
            onValueChange = {
                time = it
                timeError = !isValidTime(it) && it.isNotEmpty()
            },
            label = { Text("Hora (HH:mm)") },
            isError = timeError
        )

        if (timeError) {
            Text("Hora inválida", color = MaterialTheme.colorScheme.error)
        }

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

            when {
                client.isEmpty() || client.any { it.isDigit() } -> error = "Nombre inválido"
                !isValidPhone(phone) -> error = "Teléfono inválido"
                lane.isEmpty() || lane.toIntOrNull() == null -> error = "Pista inválida"
                !isValidDate(date) -> error = "Fecha inválida"
                !isValidTime(time) -> error = "Hora inválida"

                else -> {

                    val updated = reservation.copy(
                        clientName = client,
                        phone = phone,
                        lane = lane.toInt(),
                        date = date,
                        time = time,
                        status = status
                    )

                    viewModel.updateReservation(updated)

                    error = ""
                    onFinish()
                }
            }

        }) {
            Text("Actualizar Reserva")
        }

        if (error.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(error, color = MaterialTheme.colorScheme.error)
        }
    }
}