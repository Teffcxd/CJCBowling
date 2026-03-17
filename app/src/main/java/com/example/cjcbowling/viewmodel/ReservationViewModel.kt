package com.example.cjcbowling.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.cjcbowling.model.Reservation
import com.example.cjcbowling.repository.ReservationRepository
class ReservationViewModel(
    private val repository: ReservationRepository
) : ViewModel() {

    var reservations = mutableStateListOf<Reservation>()

    init {
        loadReservations()
    }

    fun loadReservations() {

        reservations.clear()
        reservations.addAll(repository.getAll())
    }

    fun addReservation(reservation: Reservation) {

        val exists = repository.check(
            reservation.lane,
            reservation.date,
            reservation.time
        )

        if (!exists) {

            repository.insert(reservation)

            loadReservations()
        }
    }

    fun deleteReservation(reservation: Reservation) {

        repository.delete(reservation)

        loadReservations()
    }

    fun updateReservation(reservation: Reservation) {

        repository.update(reservation)

        loadReservations()
    }

    fun searchReservation(name: String) {

        if (name.isEmpty()) {
            loadReservations()
        } else {
            reservations.clear()
            reservations.addAll(repository.search(name))
        }
    }
}