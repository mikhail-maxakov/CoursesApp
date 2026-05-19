package com.example.coursesapp.data.repository

import com.example.coursesapp.data.api.ApiService
import com.example.coursesapp.data.model.CourseDto
import com.example.coursesapp.domain.model.Course
import com.example.coursesapp.domain.repository.CourseRepository

class CourseRepositoryImpl(
    private val apiService: ApiService
) : CourseRepository {

    override suspend fun getCourses(): List<Course> {
        return apiService.getCourses().courses.map { it.toDomain() }
    }

    private fun CourseDto.toDomain(): Course = Course(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate
    )
}