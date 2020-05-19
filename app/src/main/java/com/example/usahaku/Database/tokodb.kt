package com.example.usahaku.Database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [toko::class], version = 1)
abstract class tokodb : RoomDatabase() {

    abstract fun TokoDao(): TokoDao

    companion object {
        private var instance: tokodb? = null

        fun getInstance(context: Context): tokodb? {
            if (instance == null) {
                synchronized(tokodb::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        tokodb::class.java, "toko_database"
                    )

                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }


        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }
    }

    class PopulateDbAsyncTask(db: tokodb?) : AsyncTask<Unit, Unit, Unit>() {
        private val TokoDao = db?.TokoDao()

        override fun doInBackground(vararg p0: Unit?) {
            TokoDao?.insert(toko("Coba 1", "Deskripsi 1", "annisa.raudya98@gmail.com","08108399302"))
        }
    }
}