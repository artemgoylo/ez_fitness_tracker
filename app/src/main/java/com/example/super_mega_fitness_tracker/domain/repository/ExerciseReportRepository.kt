package com.example.super_mega_fitness_tracker.domain.repository

interface ExerciseReportRepository {
    suspend fun getExerciseNames(date: Long): Result<List<String>>
}
