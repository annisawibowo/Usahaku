package com.example.usahaku.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class pembelianviewmodel(application: Application) : AndroidViewModel(application) {

    private var repository: pembelianrepositori = pembelianrepositori(application)
    private var allpembelian: LiveData<List<pembelian>> = repository.getAllpembelian()
    fun insert(pembelian: pembelian) {
        repository.insert(pembelian)
    }
    fun update(pembelian: pembelian) {
        repository.update(pembelian)
    }
    fun delete(pembelian: pembelian) {
        repository.delete(pembelian)
    }
    fun getAllpembelian(): LiveData<List<pembelian>> {
        return allpembelian
    }
}