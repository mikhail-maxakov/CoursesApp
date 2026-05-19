package com.example.coursesapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursesapp.domain.model.Course
import com.example.coursesapp.domain.usecase.GetCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class CoursesUiState {
    object Loading : CoursesUiState()
    data class Success(val courses: List<Course>) : CoursesUiState()
    data class Error(val message: String) : CoursesUiState()
}

class HomeViewModel(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CoursesUiState>(CoursesUiState.Loading)
    val uiState: StateFlow<CoursesUiState> = _uiState.asStateFlow()

    private var originalCourses: List<Course> = emptyList()
    private var isSortedDescending = false

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            _uiState.value = CoursesUiState.Loading
            try {
                val courses = getCoursesUseCase()
                originalCourses = courses
                _uiState.value = CoursesUiState.Success(courses)
            } catch (e: Exception) {
                _uiState.value = CoursesUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun toggleSort() {
        val current = _uiState.value
        if (current is CoursesUiState.Success) {
            isSortedDescending = !isSortedDescending
            val sorted = if (isSortedDescending) {
                current.courses.sortedByDescending { it.publishDate }
            } else {
                originalCourses
            }
            _uiState.value = CoursesUiState.Success(sorted)
        }
    }
}
