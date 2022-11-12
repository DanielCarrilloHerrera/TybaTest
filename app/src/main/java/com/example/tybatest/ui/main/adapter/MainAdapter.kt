package com.example.tybatest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tybatest.data.model.University
import com.example.tybatest.databinding.UniversityItemLayoutBinding

class MainAdapter(
    private var universities: List<University>,
    private val onClick: (University) -> Unit
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(
        private val binding: UniversityItemLayoutBinding,
        private val onClick: (University) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(university: University) {
            binding.textViewUniversityName.text = university.name
            binding.root.setOnClickListener{
                onClick(university)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            UniversityItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick
        )

    // override fun getItemCount(): Int = universities.size
    override fun getItemCount(): Int = if (universities.isEmpty())  0 else universities.size * 2

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(universities[position % universities.size])

    fun addData(list: List<University>) {
        universities = list
    }

}