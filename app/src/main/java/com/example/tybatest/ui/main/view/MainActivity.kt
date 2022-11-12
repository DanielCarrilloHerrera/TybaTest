package com.example.tybatest.ui.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tybatest.R
import com.example.tybatest.data.api.ApiHelper
import com.example.tybatest.data.api.ApiServiceImpl
import com.example.tybatest.data.model.University
import com.example.tybatest.databinding.ActivityMainBinding
import com.example.tybatest.ui.main.adapter.MainAdapter
import com.example.tybatest.ui.main.viewmodel.MainViewModel
import com.example.tybatest.utils.ViewModelFactory
import com.example.tybatest.utils.getInfiniteScrollForRecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        adapter = MainAdapter(listOf() , ::openDetailActivity)
        binding.rvUniversities.adapter = adapter
        // Infinite scroll
        /*binding.rvUniversities.addOnScrollListener(getInfiniteScrollForRecyclerView(
            binding.rvUniversities.layoutManager as LinearLayoutManager, adapter.itemCount))*/
    }

    private fun setupObserver() {
        mainViewModel.getUniversitiesLiveData().observe(this, Observer {
            binding.apply {
                adapter.addData(it.data ?: listOf())
                rvUniversities.apply {
                    LinearLayoutManager(context).apply {
                        layoutManager = this
                        orientation = LinearLayoutManager.VERTICAL
                        val dividerItemDecoration = DividerItemDecoration(context, orientation)
                        rvUniversities.addItemDecoration(dividerItemDecoration)
                    }
                }


            }
        })

        mainViewModel.fetchUniversities()
    }

    private fun openDetailActivity(university: University){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("university", university)
        startActivity(intent)
    }
    private fun renderList(universities: List<University>) {
        adapter.addData(universities)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(this, ViewModelFactory(ApiHelper(ApiServiceImpl()))).get(
            MainViewModel::class.java)
    }
}
