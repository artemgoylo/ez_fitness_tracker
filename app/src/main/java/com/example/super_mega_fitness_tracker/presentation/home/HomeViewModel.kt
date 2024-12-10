package com.example.super_mega_fitness_tracker.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.super_mega_fitness_tracker.domain.use.case.GetExerciseReportsByDateUseCase
import com.example.super_mega_fitness_tracker.presentation.detalization.mapper.toPresentation
import com.example.super_mega_fitness_tracker.presentation.detalization.model.ExerciseReport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar

const val DAY_IN_MILLIS = 86_400_000

class HomeViewModel(private val useCase: GetExerciseReportsByDateUseCase): ViewModel() {
    val date: StateFlow<Long?>
        field = MutableStateFlow(null)

    val reports: StateFlow<List<ExerciseReport>>
        field = MutableStateFlow(emptyList())

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
            result.fold(onSuccess = { newReports ->
                reports.update { newReports.map { it.toPresentation() } }
            }, onFailure = { e ->
                Log.d("bonjour", e.toString())
            })
        }

        date.update { roundedToDay }
    }
}