
package com.example.cjcbowling.view.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.cjcbowling.viewmodel.ReservationViewModel
import com.example.cjcbowling.view.components.ReservationItem

@Composable
fun ReservationListScreen(viewModel: ReservationViewModel) {

    var search by remember { mutableStateOf("") }

    TextField(
        value = search,
        onValueChange = {
            search = it
            viewModel.searchReservation(it)
        },
        label = { Text("Buscar cliente") }
    )

    LazyColumn {

        items(viewModel.reservations) { reservation ->

            ReservationItem(
                reservation = reservation,
                onDelete = { viewModel.deleteReservation(reservation) },
                onEdit = {}
            )
        }
    }
}