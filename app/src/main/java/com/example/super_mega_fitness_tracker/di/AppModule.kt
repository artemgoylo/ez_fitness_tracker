package com.example.super_mega_fitness_tracker.di

import com.example.super_mega_fitness_tracker.presentation.detalization.DetalizationViewModel
import com.example.super_mega_fitness_tracker.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel() }
    viewModel { DetalizationViewModel() }
}