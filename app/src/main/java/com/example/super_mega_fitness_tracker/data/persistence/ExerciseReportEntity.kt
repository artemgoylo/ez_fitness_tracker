package com.example.super_mega_fitness_tracker.data.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExerciseReportEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val setCount: Int,
    val repCount: Int,
    val weight: Int,
    val dateEpoch: Long,
)

