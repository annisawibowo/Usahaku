package com.example.usahaku.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class tokoviewmodel(application: Application) : AndroidViewModel(application) {
    private var repository: tokorepository =
        tokorepository(application)
    private var alltoko: LiveData<List<toko>> = repository.getAlltoko()

    fun insert(toko : toko) {
        repository.insert(toko)
    }

    fun update(toko : toko) {
        repository.update(toko)
    }

    fun getAlltoko(): LiveData<List<toko>> {
        return alltoko
    }
}