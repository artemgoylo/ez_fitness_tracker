package com.example.super_mega_fitness_tracker.domain.repository

import com.example.super_mega_fitness_tracker.domain.model.ExerciseReportDomainModel

interface ExerciseReportRepository {
    suspend fun getExerciseNames(date: Long): Result<List<String>>
    suspend fun getExerciseReports(date: Long): Result<List<ExerciseReportDomainModel>>
    suspend fun insertExerciseReports(reports: List<ExerciseReportDomainModel>): Result<Unit>
    suspend fun deleteExerciseReportsByDate(date: Long): Result<Unit>
}
