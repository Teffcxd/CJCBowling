package com.example.cjcbowling.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.cjcbowling.data.ReservationPreferences
import com.example.cjcbowling.repository.ReservationRepository
import com.example.cjcbowling.view.screens.DashboardScreen
import com.example.cjcbowling.viewmodel.ReservationViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val prefs = ReservationPreferences(this)
        val repository = ReservationRepository(prefs)
        val viewModel = ReservationViewModel(repository)

        setContent {
            CJCBowlingTheme {
                Surface {
                    DashboardScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun CJCBowlingTheme(content: @Composable () -> Unit) {

    val colors = darkColorScheme(
        primary = androidx.compose.ui.graphics.Color(0xFF7209B7), // morado pro
        secondary = androidx.compose.ui.graphics.Color(0xFF3A0CA3),
        background = androidx.compose.ui.graphics.Color(0xFF1E1E2F),
        surface = androidx.compose.ui.graphics.Color(0xFF2A2A40),
        onPrimary = androidx.compose.ui.graphics.Color.White,
        onBackground = androidx.compose.ui.graphics.Color.White,
        onSurface = androidx.compose.ui.graphics.Color.White
    )

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}