package com.example.coursesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coursesapp.R
import com.example.coursesapp.databinding.ItemCourseBinding
import com.example.coursesapp.domain.model.Course

fun courseAdapterDelegate() = object : CourseDelegate {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: Course
    ) {
        (holder as CourseViewHolder).bind(item)
    }
}

interface CourseDelegate {

    fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder

    fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: Course
    )
}

class CourseViewHolder(
    private val binding: ItemCourseBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(course: Course) {

        binding.tvTitle.text = course.title
        binding.tvDescription.text = course.text
        binding.tvPrice.text = course.price
        binding.tvRating.text = String.format("%.1f", course.rate)
        binding.tvDate.text = formatDate(course.startDate)

        when (course.title) {

            "Java-разработчик с нуля" -> {
                binding.ivCourse.setImageResource(R.drawable.course_java)
            }

            "3D-дженералист" -> {
                binding.ivCourse.setImageResource(R.drawable.course_3d)
            }

            else -> {
                binding.ivCourse.setImageResource(R.drawable.course_python)
            }
        }

        binding.ivLike.setImageResource(
            if (course.hasLike)
                R.drawable.ic_heart_filled
            else
                R.drawable.ic_heart_outline
        )
    }

    private fun formatDate(dateStr: String): String {

        return try {

            val parts = dateStr.split("-")

            if (parts.size >= 3) {

                val months = mapOf(
                    "01" to "янв",
                    "02" to "фев",
                    "03" to "мар",
                    "04" to "апр",
                    "05" to "май",
                    "06" to "июн",
                    "07" to "июл",
                    "08" to "авг",
                    "09" to "сен",
                    "10" to "окт",
                    "11" to "ноя",
                    "12" to "дек"
                )

                "${parts[2]} ${months[parts[1]] ?: parts[1]} ${parts[0]}"

            } else {
                dateStr
            }

        } catch (e: Exception) {
            dateStr
        }
    }
}