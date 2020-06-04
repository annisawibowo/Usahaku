package com.example.usahaku.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class supplierviewmodel(application: Application) : AndroidViewModel(application) {

    private var repository: supplierrepositori = supplierrepositori(application)
    private var allsupplier: LiveData<List<supplier>> = repository.getAllsupplier()
    fun insert(supplier: supplier) {
        repository.insert(supplier)
    }
    fun update(supplier: supplier) {
        repository.update(supplier)
    }
    fun delete(supplier: supplier) {
        repository.delete(supplier)
    }

    fun getAllsupplier(): LiveData<List<supplier>> {
        return allsupplier
    }
}