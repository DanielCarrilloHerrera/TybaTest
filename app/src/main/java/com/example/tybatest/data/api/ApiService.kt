package com.example.tybatest.data.api

import com.example.tybatest.data.model.University
import io.reactivex.Single

interface ApiService {

    fun loadUniversities(callback: (List<University>) -> Unit): Unit

}