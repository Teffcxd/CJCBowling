package com.example.cjcbowling.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cjcbowling.data.ReservationPreferences
import com.example.cjcbowling.repository.ReservationRepository
//import com.example.cjcbowling.view.screens.DashboardScreen
import com.example.cjcbowling.viewmodel.ReservationViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val prefs = ReservationPreferences(this)

        val repository = ReservationRepository(prefs)

        val viewModel = ReservationViewModel(repository)

        setContent {
            //DashboardScreen(viewModel)
        }
    }
}