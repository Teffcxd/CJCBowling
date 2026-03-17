
package com.example.cjcbowling.repository

import com.example.cjcbowling.data.ReservationPreferences
import model.Reservation

class ReservationRepository(
    private val prefs: ReservationPreferences
) {

    fun getAll(): MutableList<Reservation> {
        return prefs.getReservations()
    }

    fun insert(reservation: Reservation) {

        val list = prefs.getReservations()

        list.add(reservation)

        prefs.saveReservations(list)
    }

    fun delete(reservation: Reservation) {

        val list = prefs.getReservations()

        list.removeIf { it.id == reservation.id }

        prefs.saveReservations(list)
    }

    fun update(reservation: Reservation) {

        val list = prefs.getReservations()

        val index = list.indexOfFirst { it.id == reservation.id }

        if (index != -1) {
            list[index] = reservation
        }

        prefs.saveReservations(list)
    }

    fun search(name: String): List<Reservation> {

        return prefs.getReservations()
            .filter { it.clientName.contains(name, true) }
    }

    fun check(lane: Int, date: String, time: String): Boolean {

        return prefs.getReservations()
            .any { it.lane == lane && it.date == date && it.time == time }
    }
}