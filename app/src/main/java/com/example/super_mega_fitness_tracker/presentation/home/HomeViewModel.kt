package com.example.super_mega_fitness_tracker.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.super_mega_fitness_tracker.domain.use.case.GetDataByDateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar

const val DAY_IN_MILLIS = 86_400_000

class HomeViewModel(private val useCase: GetDataByDateUseCase): ViewModel() {
    val date: StateFlow<Long?>
        field = MutableStateFlow(null)

    fun onDateSelect(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

        val epochTime = calendar.timeInMillis

        onDateSelect(epochTime)
    }

    fun onDateSelect(dateInEpoch: Long) {
        val roundedToDay = dateInEpoch - dateInEpoch % DAY_IN_MILLIS
        viewModelScope.launch {
            val result = useCase(roundedToDay)
            result.fold(onSuccess = { names ->
                Log.d("bonjour", names.toString())
            }, onFailure = { e ->
                Log.d("bonjour", e.toString())
            })
            Log.d("bonjour", "coroutine finished")
        }
        Log.d("bonjour", "onDateSelect finished")

        date.update { roundedToDay }
    }
}