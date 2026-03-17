
package com.example.cjcbowling.view.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.cjcbowling.model.Reservation
import com.example.cjcbowling.viewmodel.ReservationViewModel

@Composable
fun EditReservationScreen(
    viewModel: ReservationViewModel,
    reservation: Reservation,
    onFinish:() ->Unit
) {

    var name by remember { mutableStateOf(reservation.clientName) }

    Column {

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Cliente") }
        )

        Button(onClick = {

            val updated = reservation.copy(clientName = name)
            viewModel.updateReservation(updated)
            onFinish()

        }) {
            Text("Actualizar")
        }
    }
}