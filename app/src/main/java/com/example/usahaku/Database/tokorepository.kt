package com.example.usahaku.Database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class tokorepository(application: Application){

    private var tokoDao: TokoDao

    private var alltoko: LiveData<List<toko>>

    init {
        val database: tokodb = tokodb.getInstance(
            application.applicationContext         )!!
        tokoDao = database.TokoDao()
        alltoko = tokoDao.getAlltoko()
    }

    fun insert( toko : toko) {
        val inserttokoAsyncTask = InserttokoAsyncTask(
            tokoDao
        ).execute(toko)     }

    fun update( toko : toko) {
        val updatetokoAsyncTask = UpdatetokoAsyncTask(
            tokoDao
        ).execute(toko)     }


    fun getAlltoko(): LiveData<List<toko>> {
        return alltoko
    }

    companion object {
        private class InserttokoAsyncTask(tokoDao: TokoDao) : AsyncTask<toko, Unit, Unit>() {
            val tokoDao = tokoDao

        override fun doInBackground(vararg p0: toko?) {
            tokoDao.insert(p0[0]!!)
        }
        }


        private class UpdatetokoAsyncTask(tokoDao: TokoDao) : AsyncTask<toko, Unit, Unit>() {
            val tokoDao = tokoDao

            override fun doInBackground(vararg p0: toko?) {
                tokoDao.update(p0[0]!!)
            }
        }




    }
}