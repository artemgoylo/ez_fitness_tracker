package com.example.super_mega_fitness_tracker.presentation.detalization

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.super_mega_fitness_tracker.common.IncrementDirection
import com.example.super_mega_fitness_tracker.domain.use.case.GetExerciseReportsByDateUseCase
import com.example.super_mega_fitness_tracker.domain.use.case.SaveExerciseReportsUseCase
import com.example.super_mega_fitness_tracker.presentation.detalization.mapper.toDomain
import com.example.super_mega_fitness_tracker.presentation.detalization.mapper.toPresentation
import com.example.super_mega_fitness_tracker.presentation.detalization.model.ExerciseReport
import com.example.super_mega_fitness_tracker.presentation.navArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val COUNT_LIMIT = 100

class DetalizationViewModel(
    savedStateHandle: SavedStateHandle,
    private val saveUseCase: SaveExerciseReportsUseCase,
    private val getReportsUseCase: GetExerciseReportsByDateUseCase,
): ViewModel() {
    private val navArgs: DetalizationScreenNavArgs = savedStateHandle.navArgs()
    val date = navArgs.date

    val exercises: StateFlow<List<ExerciseReport>>
        field = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            getReportsUseCase(date).fold(
                onSuccess = { reports -> exercises.update{ reports.map{ it.toPresentation() } } },
                onFailure = {  },
            )
        }
    }

    fun onAddCard() {
        exercises.update{ it + DEFAULT_CARD }
    }

    fun onDeleteCard(cardId: Int) {
        exercises.update {
            it.filterIndexed { index, _ -> index != cardId }
        }
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
                    report.copy(weight = value) //TODO: fix cursor jankiness
                } else report
            }
        }
    }

    fun onSave() {
        viewModelScope.launch {
            saveUseCase(date, exercises.value.map { it.toDomain(date) })
        }
    }

    private companion object {
        val DEFAULT_CARD = ExerciseReport(name = "", setCount = 0, repCount = 0, weight = "")
    }
}
