package com.example.solicitudesmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.solicitudesmobile.ui.navigation.AppNav
import com.example.solicitudesmobile.ui.theme.SolicitudesMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SolicitudesMobileTheme {
                AppNav()
            }
        }
    }
}