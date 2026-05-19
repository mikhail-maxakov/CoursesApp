package com.example.coursesapp.data.model

import com.google.gson.annotations.SerializedName

data class CoursesResponse(
    @SerializedName("courses")
    val courses: List<CourseDto>
)