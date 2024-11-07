package com.example.super_mega_fitness_tracker.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.super_mega_fitness_tracker.presentation.ui.theme.SupermegafitnesstrackerTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            SupermegafitnesstrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}
