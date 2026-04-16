package com.example.safepass_g2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safepass_g2.ui.registro.RegistroScreen
import com.example.safepass_g2.ui.registro.RegistroViewModel
import com.example.safepass_g2.ui.theme.SafePass_G2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            SafePass_G2Theme {
                val viewModel: RegistroViewModel = viewModel()
                RegistroScreen(viewModel = viewModel)
            }
        }
    }
}