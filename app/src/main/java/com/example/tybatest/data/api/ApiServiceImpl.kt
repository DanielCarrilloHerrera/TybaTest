package com.example.tybatest.data.api

import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.tybatest.AppController
import com.example.tybatest.data.model.University


class ApiServiceImpl : ApiService {



    override fun loadUniversities(callback: (List<University>) -> Unit): Unit {
    val universitiesList = mutableListOf<University>()
        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET,
            "https://tyba-assets.s3.amazonaws.com/FE-Engineer-test/universities.json",
            null,
            {
                for (i in 0 until (it.length() - 1)){

                    val jsonObject = it.getJSONObject(i)

                    val domains = mutableListOf<String>()
                    val webPages = mutableListOf<String>()

                    val domainArray = jsonObject.getJSONArray("domains")
                    val webArray = jsonObject.getJSONArray("web_pages")

                    for (j in 0 until domainArray.length()){
                        domains.add(domainArray.getString(j))
                    }

                    for (k in 0 until (webArray.length() - 1)){
                        webPages.add(webArray.getString(k))
                    }

                    val user = University(
                        jsonObject.getString("alpha_two_code"),
                        domains,
                        jsonObject.getString("country"),
                        jsonObject.getString("state-province"),
                        webPages,
                        jsonObject.getString("name")
                    )

                    universitiesList.add(user)
                }

                callback(universitiesList)
            },
            {
                it
            })

        //adding the request to request queue
        AppController.instance?.addToRequestQueue((jsonObjectRequest))
    }

}