package com.example.super_mega_fitness_tracker.domain.use.case

import com.example.super_mega_fitness_tracker.domain.repository.ExerciseReportRepository

class GetDataByDateUseCase(private val repository: ExerciseReportRepository) {
    suspend operator fun invoke(date: Long): Result<List<String>> = repository.getExerciseNames(date)
}