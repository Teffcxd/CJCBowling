package com.example.cjcbowling.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cjcbowling.model.Reservation
import com.example.cjcbowling.viewmodel.ReservationViewModel

fun isValidDate(date: String): Boolean {
    return try {
        val parts = date.split("/")
        if (parts.size != 3) return false

        val day = parts[0].toInt()
        val month = parts[1].toInt()
        val year = parts[2].toInt()

        day in 1..31 && month in 1..12 && year >= 2024
    } catch (e: Exception) {
        false
    }
}

fun isValidTime(time: String): Boolean {
    return try {
        val parts = time.split(":")
        if (parts.size != 2) return false

        val hour = parts[0].toInt()
        val minute = parts[1].toInt()

        hour in 0..23 && minute in 0..59
    } catch (e: Exception) {
        false
    }
}

fun isValidPhone(phone: String): Boolean {
    return phone.all { it.isDigit() } && phone.length >= 7
}

@Composable
fun AddReservationScreen(viewModel: ReservationViewModel, onFinish: () -> Unit) {

    var client by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var lane by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var dateError by remember { mutableStateOf(false) }
    var timeError by remember { mutableStateOf(false) }
    var clientError by remember { mutableStateOf(false) }


    Column(modifier = Modifier.padding(16.dp)) {

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
            Text(
                "Nombre inválido (no números)",
                color = MaterialTheme.colorScheme.error
            )
        }

        when {
            client.isEmpty() || client.any { it.isDigit() } ->
                error = "Nombre inválido"
        }
        TextField(value = phone, onValueChange = { phone = it }, label = { Text("Teléfono") })
        TextField(value = lane, onValueChange = { lane = it }, label = { Text("Pista") })
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
            Text("Formato inválido", color = MaterialTheme.colorScheme.error)
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

        Button(onClick = {

            when {
                client.isEmpty() -> error = "El cliente es obligatorio"
                !isValidPhone(phone) -> error = "Teléfono inválido"
                lane.isEmpty() || lane.toIntOrNull() == null -> error = "Pista inválida"
                !isValidDate(date) -> error = "Fecha inválida (dd/mm/yyyy)"
                !isValidTime(time) -> error = "Hora inválida (HH:mm)"

                else -> {

                    val exists = viewModel.reservations.any {
                        it.lane == lane.toInt() && it.date == date && it.time == time
                    }

                    if (exists) {
                        error = "Esa pista ya está reservada en ese horario"
                        return@Button
                    }

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
                    error = ""

                    onFinish()
                }
            }

        }) {
            Text("Guardar Reserva")
        }

        if (error.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(error, color = MaterialTheme.colorScheme.error)
        }
    }
}