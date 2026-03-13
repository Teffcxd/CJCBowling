package data

import android.content.Context
import model.Reservation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferenceManager(context: Context) {

    private val prefs = context.getSharedPreferences("reservations_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveReservations(reservations: List<Reservation>) {

        val json = gson.toJson(reservations)
        prefs.edit()
            .putString("reservations", json)
            .apply()
    }

    fun getReservations(): MutableList<Reservation> {
        val json = prefs.getString("reservations", null)
        if (json == null) return mutableListOf()
        val type = object : TypeToken<MutableList<Reservation>>() {}.type
        return gson.fromJson(json, type)
    }
}