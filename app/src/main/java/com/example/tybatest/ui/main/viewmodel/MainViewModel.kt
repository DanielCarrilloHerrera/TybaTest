package com.example.tybatest.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tybatest.data.model.University
import com.example.tybatest.data.repository.MainRepository
import com.example.tybatest.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val universities = MutableLiveData<Resource<List<University>>>()
    private val compositeDisposable = CompositeDisposable()

    fun fetchUniversities() {
        universities.postValue(Resource.loading(null))
        mainRepository.loadUniversities(::updateUniversitiesLiveData)
        /*compositeDisposable.add(
            mainRepository.getUniversities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ universitiesList ->
                    universities.postValue(Resource.success(universitiesList))
                }, {
                    universities.postValue(Resource.error("Something Went Wrong", null))
                })
        )*/
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun updateUniversitiesLiveData(universities : List<University>){
        this.universities.value = Resource.success(universities)
    }

    fun getUniversitiesLiveData(): LiveData<Resource<List<University>>> {
        return universities
    }

}