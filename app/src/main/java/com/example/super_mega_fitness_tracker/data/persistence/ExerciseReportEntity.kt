package com.example.super_mega_fitness_tracker.data.persistence

import androidx.room.Entity

@Entity(primaryKeys = ["daysIndex", "dateEpoch"])
data class ExerciseReportEntity(
    val daysIndex: Int,
    val dateEpoch: Long,
    val name: String,
    val setCount: Int,
    val repCount: Int,
    val weight: Int,
)

