package com.example.super_mega_fitness_tracker.domain.use.case

import com.example.super_mega_fitness_tracker.domain.model.ExerciseReportDomainModel
import com.example.super_mega_fitness_tracker.domain.repository.ExerciseReportRepository

class SaveExerciseReportsUseCase(private val repository: ExerciseReportRepository) {
    suspend operator fun invoke(reports: List<ExerciseReportDomainModel>): Result<Unit> = repository.insertExerciseReports(reports)
}