package com.example.tybatest.data.api

import com.example.tybatest.data.model.University

class ApiHelper(private val apiService: ApiService) {

    fun loadUniversities(callback: (List<University>) -> Unit) = apiService.loadUniversities(callback)

}