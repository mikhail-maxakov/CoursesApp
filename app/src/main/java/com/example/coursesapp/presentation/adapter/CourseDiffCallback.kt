package com.example.coursesapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.coursesapp.domain.model.Course

class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean =
        oldItem == newItem
}
