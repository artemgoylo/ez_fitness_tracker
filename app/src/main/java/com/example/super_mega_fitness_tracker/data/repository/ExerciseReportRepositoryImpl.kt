package com.example.super_mega_fitness_tracker.data.repository

import com.example.super_mega_fitness_tracker.data.mapper.toEntity
import com.example.super_mega_fitness_tracker.data.persistence.ExerciseReportDao
import com.example.super_mega_fitness_tracker.domain.model.ExerciseReportDomainModel
import com.example.super_mega_fitness_tracker.domain.repository.ExerciseReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseReportRepositoryImpl(private val dao: ExerciseReportDao): ExerciseReportRepository {
    override suspend fun getExerciseNames(date: Long): Result<List<String>> = withContext(Dispatchers.IO) {
        Result.runCatching {
             dao.getAll(date).map { it.name }
        }
    }

    override suspend fun insertExerciseReports(reports: List<ExerciseReportDomainModel>): Result<Unit> = withContext(Dispatchers.IO) {
        Result.runCatching {
            val entities = reports.map(ExerciseReportDomainModel::toEntity)
            dao.insertAll(*entities.toTypedArray())
        }
    }
}