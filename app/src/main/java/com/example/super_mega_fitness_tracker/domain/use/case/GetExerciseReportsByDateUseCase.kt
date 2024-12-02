package com.example.super_mega_fitness_tracker.domain.use.case

import com.example.super_mega_fitness_tracker.domain.model.ExerciseReportDomainModel
import com.example.super_mega_fitness_tracker.domain.repository.ExerciseReportRepository

class GetExerciseReportsByDateUseCase(private val repository: ExerciseReportRepository) {
    suspend operator fun invoke(date: Long): Result<List<ExerciseReportDomainModel>> = repository.getExerciseReports(date)
}