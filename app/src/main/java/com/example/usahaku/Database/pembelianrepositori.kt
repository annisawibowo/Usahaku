package com.example.usahaku.Database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


class pembelianrepositori(application: Application) {

    private var pembeliandao:pembeliandao

    private var allpembelian: LiveData<List<pembelian>>

    init {
        val database: pembeliandb = pembeliandb.getInstance(
            application.applicationContext
        )!!
        pembeliandao = database.pembeliandao()
        allpembelian = pembeliandao.getAllpembelian()

    }
    fun insert(pembelian: pembelian) {
        val insertpembelianAsyncTask = InsertpembelianAsyncTask(pembeliandao).execute(pembelian)
    }
    fun update(pembelian: pembelian) {
        val updatepembelianAsyncTask = UpdatepembelianAsyncTask(pembeliandao).execute(pembelian)
    }
    fun delete(pembelian: pembelian) {
        val deletepembelianAsyncTask = DeletepembelianAsyncTask(pembeliandao).execute(pembelian)
    }

    fun getAllpembelian(): LiveData<List<pembelian>> {
        return allpembelian
    }

    companion object {
        private class InsertpembelianAsyncTask(pembeliandao: pembeliandao) : AsyncTask<pembelian, Unit, Unit>() {
            val pembeliandao = pembeliandao
            override fun doInBackground(vararg p0: pembelian?) {
                pembeliandao.insert(p0[0]!!)
            }
        }
        private class UpdatepembelianAsyncTask(pembeliandao: pembeliandao) : AsyncTask<pembelian, Unit, Unit>() {
            val pembeliandao = pembeliandao

            override fun doInBackground(vararg p0: pembelian?) {
                pembeliandao.update(p0[0]!!)
            }
        }
        private class DeletepembelianAsyncTask(pembeliandao: pembeliandao) : AsyncTask<pembelian, Unit, Unit>() {
            val pembeliandao = pembeliandao

            override fun doInBackground(vararg p0: pembelian?) {
                pembeliandao.delete(p0[0]!!)
            }
        }


    }


}