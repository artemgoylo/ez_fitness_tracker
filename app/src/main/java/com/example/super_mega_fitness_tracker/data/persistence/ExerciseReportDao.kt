package com.example.super_mega_fitness_tracker.data.persistence

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ExerciseReportDao {
    @Upsert
    fun insertAll(vararg cards: ExerciseReportEntity)

    @Query("SELECT * FROM exercisereportentity WHERE dateEpoch = :date")
    fun getAll(date: Long): List<ExerciseReportEntity>
}