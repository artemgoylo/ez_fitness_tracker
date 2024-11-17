package com.example.super_mega_fitness_tracker.data.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExerciseReportEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val setCount: Int,
    val repCount: Int,
    val weight: Int,
    val dateEpoch: Long,
)

