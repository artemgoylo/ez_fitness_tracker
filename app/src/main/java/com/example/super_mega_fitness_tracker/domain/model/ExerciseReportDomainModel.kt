package com.example.super_mega_fitness_tracker.domain.model

data class ExerciseReportDomainModel(
    val name: String,
    val setCount: Int,
    val repCount: Int,
    val weight: Int,
    val dateEpoch: Long,
)
