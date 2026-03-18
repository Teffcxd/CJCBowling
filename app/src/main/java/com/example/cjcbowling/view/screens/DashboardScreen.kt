package com.example.cjcbowling.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cjcbowling.model.Reservation
import com.example.cjcbowling.viewmodel.ReservationViewModel
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun DashboardScreen(viewModel: ReservationViewModel) {

    var screen by remember { mutableStateOf("home") }
    var selectedReservation by remember { mutableStateOf<Reservation?>(null) }

    val reservations = viewModel.reservations

    val total = reservations.size
    val active = reservations.count { it.status == "Activa" }
    val finished = reservations.count { it.status == "Finalizada" }

    // 🔥 NUEVO: Próxima reserva
    val nextReservation = reservations
        .filter { it.status == "Activa" }
        .minByOrNull { it.date + it.time }

    // 🔥 NUEVO: Reservas de hoy (simple)
    val today = "18/03/2026" // luego lo puedes hacer dinámico
    val todayReservations = reservations.filter { it.date == today }

    when (screen) {

        "home" -> {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF2C2C54),
                                Color(0xFF5A189A),
                                Color(0xFF9D4EDD)
                            )
                        )
                    )
                    .padding(16.dp)
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Text(
                        text = "🎳 CJC Bowling",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White
                    )

                    // 👋 NUEVO: Bienvenida
                    Text(
                        text = "👋 Bienvenido",
                        color = Color.White
                    )

                    Text(
                        text = "Gestiona tus reservas fácilmente",
                        color = Color.White
                    )

                    // 🎯 NUEVO: Próxima reserva
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text("🎯 Próxima reserva")

                            if (nextReservation != null) {
                                Text("Cliente: ${nextReservation.clientName}")
                                Text("Hora: ${nextReservation.time}")
                                Text("Pista: ${nextReservation.lane}")
                            } else {
                                Text("No hay reservas próximas")
                            }
                        }
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(12.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            Text(
                                "📊 Resumen",
                                style = MaterialTheme.typography.titleLarge
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Total: $total")
                                Text("Activas: $active")
                                Text("Finalizadas: $finished")
                            }
                        }
                    }

                    // 📅 NUEVO: reservas de hoy
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Text(
                            text = "📅 Hoy: ${todayReservations.size} reservas",
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            Button(
                                onClick = { screen = "list" },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("📋 Ver Reservas")
                            }

                            Button(
                                onClick = { screen = "add" },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("➕ Nueva Reserva")
                            }
                        }
                    }
                }
            }
        }

        "add" -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {

                Button(
                    onClick = { screen = "home" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("⬅ Volver")
                }

                AddReservationScreen(
                    viewModel,
                    onFinish = { screen = "home" }
                )
            }
        }

        "list" -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {

                Button(
                    onClick = { screen = "home" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("⬅ Volver")
                }

                ReservationListScreen(
                    viewModel,
                    onEdit = {
                        selectedReservation = it
                        screen = "edit"
                    }
                )
            }
        }

        "edit" -> {
            selectedReservation?.let {

                EditReservationScreen(
                    viewModel = viewModel,
                    reservation = it,
                    onFinish = { screen = "list" }
                )
            }
        }
    }
}