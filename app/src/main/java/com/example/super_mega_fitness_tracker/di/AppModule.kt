package com.example.super_mega_fitness_tracker.di

import androidx.room.Room
import com.example.super_mega_fitness_tracker.data.persistence.AppDatabase
import com.example.super_mega_fitness_tracker.data.repository.ExerciseReportRepositoryImpl
import com.example.super_mega_fitness_tracker.domain.repository.ExerciseReportRepository
import com.example.super_mega_fitness_tracker.domain.use.case.GetDataByDateUseCase
import com.example.super_mega_fitness_tracker.domain.use.case.SaveExerciseReportsUseCase
import com.example.super_mega_fitness_tracker.presentation.detalization.DetalizationViewModel
import com.example.super_mega_fitness_tracker.presentation.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetalizationViewModel(get(), get()) }
    factory { GetDataByDateUseCase(get()) }
    factory { SaveExerciseReportsUseCase(get()) }
    factory { ExerciseReportRepositoryImpl(get()) } bind ExerciseReportRepository::class
    factory {
        val db = Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "super-mega-fitness-tracker-db"
        ).build()
        db.exerciseReportDao()
    }
}