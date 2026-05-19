package com.example.coursesapp.app.di

import com.example.coursesapp.data.api.ApiService
import com.example.coursesapp.data.api.RetrofitClient
import com.example.coursesapp.data.repository.CourseRepositoryImpl
import com.example.coursesapp.domain.repository.CourseRepository
import com.example.coursesapp.domain.usecase.GetCoursesUseCase
import com.example.coursesapp.presentation.home.HomeViewModel
import com.example.coursesapp.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ApiService> { RetrofitClient.create() }
    single<CourseRepository> { CourseRepositoryImpl(get()) }
    factory { GetCoursesUseCase(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel() }
}
