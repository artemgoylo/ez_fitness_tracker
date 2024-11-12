package com.example.super_mega_fitness_tracker.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.super_mega_fitness_tracker.domain.use.case.GetDataByDateUseCase
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeViewModel(private val useCase: GetDataByDateUseCase): ViewModel() {
    fun onDateSelect(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val epochTime = calendar.timeInMillis
        Log.d("bonjour", "Selected date epoch time: $epochTime")

        viewModelScope.launch {
            val result = useCase(epochTime)
            result.fold(onSuccess = {  }, onFailure = {  })
            Log.d("bonjour", "coroutine finished")
        }
        Log.d("bonjour", "onDateSelect finished")
    }
}