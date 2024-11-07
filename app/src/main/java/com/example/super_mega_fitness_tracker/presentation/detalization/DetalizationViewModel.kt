package com.example.super_mega_fitness_tracker.presentation.detalization

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.example.super_mega_fitness_tracker.common.IncrementDirection
import com.example.super_mega_fitness_tracker.presentation.detalization.model.ExerciseReport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

const val COUNT_LIMIT = 100

class DetalizationViewModel: ViewModel() {
    val exercises: StateFlow<List<ExerciseReport>>
        field = MutableStateFlow(emptyList())

    fun onAddCard() {
        exercises.update{ it + DEFAULT_CARD }
    }

    fun onNameChange(value: String, cardId: Int) {
        exercises.update {
            it.mapIndexed { index, report ->
                if (cardId == index) {
                    report.copy(name = value)
                } else report
            }
        }
    }

    fun onSetChange(direction: IncrementDirection, cardId: Int) {
        exercises.update {
            it.mapIndexed { index, report ->
                if (cardId == index) {
                    if (direction == IncrementDirection.LEFT)
                        report.copy(setCount = (report.setCount - 1).coerceAtLeast(0))
                    else
                        report.copy(setCount = (report.setCount + 1).coerceAtMost(COUNT_LIMIT))
                } else report
            }
        }
    }

    fun onRepChange(direction: IncrementDirection, cardId: Int) {
        exercises.update{
            it.mapIndexed { index, report ->
                if (cardId == index) {
                    if (direction == IncrementDirection.LEFT)
                        report.copy(repCount = (report.repCount - 1).coerceAtLeast(0))
                    else
                        report.copy(repCount = (report.repCount + 1).coerceAtMost(COUNT_LIMIT))
                } else report
            }
        }
    }

    fun onWeightChange(value: String, cardId: Int) {
        if (value.isDigitsOnly().not() || value.length !in 0 until 4) return
        exercises.update {
            it.mapIndexed { index, report ->
                if (cardId == index) {
                    report.copy(weight = value.toIntOrNull() ?: 0)
                } else report
            }
        }
    }

    private companion object {
        val DEFAULT_CARD = ExerciseReport(name = "", setCount = 0, repCount = 0, weight = 0)
    }
}
