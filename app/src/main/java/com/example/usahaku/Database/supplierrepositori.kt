package com.example.usahaku.Database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


class supplierrepositori(application: Application) {

    private var supplierdao : supplierdao

    private var allsupplier: LiveData<List<supplier>>

    init {
        val database: supplierdb = supplierdb.getInstance(
            application.applicationContext
        )!!
        supplierdao = database.supplierdao()
        allsupplier = supplierdao.getAllsupplier()

    }
    fun insert(supplier: supplier) {
        val insertsupplierAsyncTask = InsertsupplierAsyncTask(supplierdao).execute(supplier)
    }
    fun update(supplier: supplier) {
        val updatesupplierAsyncTask = UpdatesupplierAsyncTask(supplierdao).execute(supplier)
    }
    fun delete(supplier: supplier) {
        val deletesupplierAsyncTask = DeletesupplierAsyncTask(supplierdao).execute(supplier)
    }

    fun getAllsupplier(): LiveData<List<supplier>> {
        return allsupplier
    }

    companion object {
        private class InsertsupplierAsyncTask(supplierdao: supplierdao) : AsyncTask<supplier, Unit, Unit>() {
            val supplierdao = supplierdao
            override fun doInBackground(vararg p0: supplier?) {
                supplierdao.insert(p0[0]!!)
            }
        }
        private class UpdatesupplierAsyncTask(supplierdao: supplierdao) : AsyncTask<supplier, Unit, Unit>() {
            val supplierdao= supplierdao

            override fun doInBackground(vararg p0: supplier?) {
                supplierdao.update(p0[0]!!)
            }
        }
        private class DeletesupplierAsyncTask(supplierdao: supplierdao) : AsyncTask<supplier, Unit, Unit>() {
            val supplierdao = supplierdao

            override fun doInBackground(vararg p0: supplier?) {
                supplierdao.delete(p0[0]!!)
            }
        }


    }


}