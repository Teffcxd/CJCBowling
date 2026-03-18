package com.example.cjcbowling.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cjcbowling.model.Reservation

@Composable
fun ReservationItem(
    reservation: Reservation,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            // Nombre del cliente (destacado)
            Text(
                text = reservation.clientName,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Info secundaria
            Text("📅 Fecha: ${reservation.date} ")
            Text("⌚ Hora: ${reservation.time}")
            Text("🎳 Pista: ${reservation.lane}")
            Text("📞 Telefono: ${reservation.phone}")

            Spacer(modifier = Modifier.height(8.dp))

            // Estado con color
            val statusColor = when (reservation.status) {
                "Activa" -> androidx.compose.ui.graphics.Color(0xFF2ECC71)
                else -> androidx.compose.ui.graphics.Color(0xFF95A5A6)
            }

            Surface(
                color = statusColor.copy(alpha = 0.2f),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = reservation.status,
                    color = statusColor,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Botones alineados a la derecha
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                Button(
                    onClick = onEdit,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("✏ Editar")
                }

                Button(
                    onClick = onDelete,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("🗑 Eliminar")
                }
            }
        }
    }
}