package com.example.coursesapp.domain.usecase

import com.example.coursesapp.domain.model.Course
import com.example.coursesapp.domain.repository.CourseRepository

class GetCoursesUseCase(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(): List<Course> = repository.getCourses()
}
