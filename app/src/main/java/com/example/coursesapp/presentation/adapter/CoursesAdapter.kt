package com.example.coursesapp.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coursesapp.domain.model.Course

class CoursesAdapter : ListAdapter<Course, RecyclerView.ViewHolder>(CourseDiffCallback()) {

    private val delegate = courseAdapterDelegate()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegate.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegate.onBindViewHolder(holder, getItem(position))
    }
}
