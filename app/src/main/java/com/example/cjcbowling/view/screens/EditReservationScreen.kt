package com.example.cjcbowling.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF5a189a), // Tu color original
                        Color(0xFF9567bc), // Tono más claro
                        Color(0xFFcbb1de)
                    )
                )
            )
            .padding(16.dp)
    ) {

        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "🎳 Editar Reserva",
                    style = MaterialTheme.typography.headlineSmall
                )

                OutlinedTextField(
                    value = client,
                    onValueChange = {
                        client = it
                        clientError = it.isEmpty() || it.any { char -> char.isDigit() }
                    },
                    label = { Text("Cliente") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = clientError
                )

                if (clientError) {
                    Text("Nombre inválido", color = MaterialTheme.colorScheme.error)
                }

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = lane,
                    onValueChange = { lane = it },
                    label = { Text("Pista") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = date,
                    onValueChange = {
                        date = it
                        dateError = !isValidDate(it) && it.isNotEmpty()
                    },
                    label = { Text("Fecha (dd/mm/yyyy)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = dateError
                )

                if (dateError) {
                    Text("Fecha inválida", color = MaterialTheme.colorScheme.error)
                }

                OutlinedTextField(
                    value = time,
                    onValueChange = {
                        time = it
                        timeError = !isValidTime(it) && it.isNotEmpty()
                    },
                    label = { Text("Hora (HH:mm)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = timeError
                )

                if (timeError) {
                    Text("Hora inválida", color = MaterialTheme.colorScheme.error)
                }

                Text("Estado", style = MaterialTheme.typography.titleMedium)

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                    Button(
                        onClick = { status = "Activa" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (status == "Activa")
                                androidx.compose.ui.graphics.Color(0xFF2ECC71)
                            else MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Text("Activa")
                    }

                    Button(
                        onClick = { status = "Finalizada" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (status == "Finalizada")
                                androidx.compose.ui.graphics.Color(0xFF2ECC71)
                            else MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Text("Finalizada")
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
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
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Actualizar Reserva")
                }

                if (error.isNotEmpty()) {
                    Text(error, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}