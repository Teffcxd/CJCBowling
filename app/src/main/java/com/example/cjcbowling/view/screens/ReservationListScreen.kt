package com.example.cjcbowling.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cjcbowling.viewmodel.ReservationViewModel
import com.example.cjcbowling.view.components.ReservationItem
import com.example.cjcbowling.model.Reservation

@Composable
fun ReservationListScreen(viewModel: ReservationViewModel, onEdit: (Reservation) -> Unit) {

    var search by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        TextField(
            value = search,
            onValueChange = {
                search = it
                viewModel.searchReservation(it)
            },
            label = { Text("Buscar cliente") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {

            items(viewModel.reservations) { reservation ->

                ReservationItem(
                    reservation = reservation,
                    onDelete = { viewModel.deleteReservation(reservation) },
                    onEdit = { onEdit(reservation) }
                )
            }
        }
    }
}