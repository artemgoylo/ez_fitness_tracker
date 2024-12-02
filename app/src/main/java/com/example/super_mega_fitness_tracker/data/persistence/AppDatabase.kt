package com.example.super_mega_fitness_tracker.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ExerciseReportEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseReportDao(): ExerciseReportDao
}