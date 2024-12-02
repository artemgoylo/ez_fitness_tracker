package com.example.super_mega_fitness_tracker.data.mapper

import com.example.super_mega_fitness_tracker.data.persistence.ExerciseReportEntity
import com.example.super_mega_fitness_tracker.domain.model.ExerciseReportDomainModel

fun ExerciseReportDomainModel.toEntity(index: Int) = ExerciseReportEntity(
    name = name,
    setCount = setCount,
    repCount = repCount,
    weight = weight,
    dateEpoch = dateEpoch,
    daysIndex = index,
)

fun ExerciseReportEntity.toDomain() = ExerciseReportDomainModel(
    name = name,
    setCount = setCount,
    repCount = repCount,
    weight = weight,
    dateEpoch = dateEpoch,
)