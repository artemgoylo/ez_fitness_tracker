package com.example.super_mega_fitness_tracker.presentation.detalization.mapper

import com.example.super_mega_fitness_tracker.domain.model.ExerciseReportDomainModel
import com.example.super_mega_fitness_tracker.presentation.detalization.model.ExerciseReport

fun ExerciseReport.toDomain(dateEpoch: Long) = ExerciseReportDomainModel(
    name = name,
    setCount = setCount,
    repCount = repCount,
    dateEpoch = dateEpoch,
    weight = weight.toIntOrNull() ?: 0
)

fun ExerciseReportDomainModel.toPresentation() = ExerciseReport(
    name = name,
    setCount = setCount,
    repCount = repCount,
    weight = weight.toString(),
)