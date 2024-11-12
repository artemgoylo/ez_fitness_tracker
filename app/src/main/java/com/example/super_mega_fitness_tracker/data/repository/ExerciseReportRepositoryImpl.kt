package com.example.super_mega_fitness_tracker.data.repository

import com.example.super_mega_fitness_tracker.data.persistence.ExerciseReportDao
import com.example.super_mega_fitness_tracker.domain.repository.ExerciseReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseReportRepositoryImpl(private val dao: ExerciseReportDao): ExerciseReportRepository {
    override suspend fun getExerciseNames(date: Long): Result<List<String>> {
        return withContext(Dispatchers.IO) {
            Result.runCatching {
                 dao.getAll(date).map { it.name }
            }
        }
    }
}