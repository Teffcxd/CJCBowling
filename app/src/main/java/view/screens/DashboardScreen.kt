
package com.example.cjcbowling.view.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.cjcbowling.viewmodel.ReservationViewModel

@Composable
fun DashboardScreen(viewModel: ReservationViewModel) {

    val total = viewModel.reservations.size
    val active = viewModel.reservations.count { it.status == "Activa" }
    val finished = viewModel.reservations.count { it.status == "Finalizada" }

    Text("Total reservas: $total")
    Text("Reservas activas: $active")
    Text("Reservas finalizadas: $finished")
}