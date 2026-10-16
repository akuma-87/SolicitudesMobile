
    package com.example.solicitudesmobile

    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import com.example.solicitudesmobile.ui.screens.SolicitudesScreen
    import com.example.solicitudesmobile.ui.theme.SolicitudesMobileTheme

    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                SolicitudesMobileTheme {
                    SolicitudesScreen()
                }
            }
        }
    }