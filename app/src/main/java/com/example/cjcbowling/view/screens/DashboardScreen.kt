package com.example.cjcbowling.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cjcbowling.viewmodel.ReservationViewModel

@Composable
fun DashboardScreen(viewModel: ReservationViewModel) {

    var screen by remember { mutableStateOf("home") }
    var selectedReservation by remember { mutableStateOf<com.example.cjcbowling.model.Reservation?>(null) }

    val reservations = viewModel.reservations

    val total = reservations.size
    val active = reservations.count { it.status == "Activa" }
    val finished = reservations.count { it.status == "Finalizada" }

    when (screen) {

        "home" -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {

                Text(
                    text = "🎳 CJC Bowling 🎳 ",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { screen = "list" },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver Reservas")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { screen = "add" },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Nueva Reserva")
                }

                Spacer(modifier = Modifier.height(30.dp))

                Card(modifier = Modifier.fillMaxWidth()) {

                    Column(modifier = Modifier.padding(16.dp)) {

                        Text("📊 Resumen")

                        Spacer(modifier = Modifier.height(10.dp))

                        Text("Total: $total")
                        Text("Activas: $active")
                        Text("Finalizadas: $finished")
                    }
                }
            }
        }

        "add" -> {

            Column {

                Button(onClick = { screen = "home" }) {
                    Text("⬅ Volver")
                }

                AddReservationScreen(viewModel,
                onFinish = { screen ="home"}
                )
            }
        }

        "list" -> {

            Column {

                Button(onClick = { screen = "home" }) {
                    Text("⬅ Volver")
                }

                ReservationListScreen(
                    viewModel,
                    onEdit ={
                    selectedReservation = it
                    screen = "edit"
                })
            }
        }

        "edit" -> {
            selectedReservation?.let {

                Column {
                    Button(onClick = { screen = "list" }) {
                        Text("⬅ Volver")
                    }

                    EditReservationScreen(
                        viewModel,
                        reservation = it,
                        onFinish = { screen = "list" }
                    )
                }
            }
        }
    }
}