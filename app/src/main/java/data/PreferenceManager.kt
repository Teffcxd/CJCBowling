package com.example.bowlingapp.data

import android.content.Context
import model.Reservation
import org.json.JSONArray
import org.json.JSONObject
// CJ Parte de los dos
class ReservationPreferences(context: Context) {

    private val prefs =
        context.getSharedPreferences("reservations_db", Context.MODE_PRIVATE)

    fun getReservations(): MutableList<Reservation> {

        val json = prefs.getString("reservations", "[]")

        val list = mutableListOf<Reservation>()

        val jsonArray = JSONArray(json)

        for (i in 0 until jsonArray.length()) {

            val obj = jsonArray.getJSONObject(i)

            list.add(
                Reservation(
                    id = obj.getInt("id"),
                    clientName = obj.getString("clientName"),
                    phone = obj.getString("phone"),
                    lane = obj.getInt("lane"),
                    date = obj.getString("date"),
                    time = obj.getString("time"),
                    status = obj.getString("status")
                )
            )
        }

        return list
    }

    fun saveReservations(reservations: List<Reservation>) {

        val jsonArray = JSONArray()

        reservations.forEach {

            val obj = JSONObject()

            obj.put("id", it.id)
            obj.put("clientName", it.clientName)
            obj.put("phone", it.phone)
            obj.put("lane", it.lane)
            obj.put("date", it.date)
            obj.put("time", it.time)
            obj.put("status", it.status)

            jsonArray.put(obj)
        }

        prefs.edit()
            .putString("reservations", jsonArray.toString())
            .apply()
    }
}