package com.example.tybatest.data.repository

import com.example.tybatest.data.api.ApiHelper
import com.example.tybatest.data.model.University
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun loadUniversities(callback: (List<University>) -> Unit) {
        apiHelper.loadUniversities(callback)
    }

}