package view.Screen


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import model.Reservation
import com.example.bowlingapp.viewmodel.ReservationViewModel


@Composable
fun AddReservationScreen(
    viewModel: ReservationViewModel,
    onBack: () -> Unit
) {

    var client by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var lane by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    var showMessage by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Nueva Reserva",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                TextField(
                    value = client,
                    onValueChange = { client = it },
                    label = { Text("Nombre del cliente") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Teléfono") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = lane,
                    onValueChange = { lane = it },
                    label = { Text("Número de pista") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Fecha") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = time,
                    onValueChange = { time = it },
                    label = { Text("Hora") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                        val reservation = Reservation(
                            id = System.currentTimeMillis().toInt(),
                            clientName = client,
                            phone = phone,
                            lane = lane.toIntOrNull() ?: 0,
                            date = date,
                            time = time,
                            status = "Activa"
                        )

                        viewModel.addReservation(reservation)

                        showMessage = true
                    }
                ) {
                    Text("Guardar Reserva")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onBack
        ) {
            Text("Volver al menú")
        }
    }

    if (showMessage) {

        AlertDialog(
            onDismissRequest = { showMessage = false },
            title = { Text("Reserva guardada") },
            text = { Text("La reserva se registró correctamente.") },
            confirmButton = {
                Button(
                    onClick = {
                        showMessage = false
                        onBack()
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}
