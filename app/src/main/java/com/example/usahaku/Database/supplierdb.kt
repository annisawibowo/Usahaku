package com.example.usahaku.Database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [supplier::class], version = 1)
abstract class supplierdb : RoomDatabase() {

    abstract fun supplierdao(): supplierdao
    companion object {
        private var instance: supplierdb? = null
        fun getInstance(context: Context): supplierdb? {
            if (instance == null) {
                synchronized(supplierdb::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        supplierdb::class.java, "supplier_database"  )
                        .fallbackToDestructiveMigration() .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }
        fun destroyInstance() {
            instance = null
        }
        private val roomCallback = object :
            RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }
    }

    class PopulateDbAsyncTask(db:supplierdb?) : AsyncTask<Unit, Unit, Unit>() {
        private val supplierdao = db?.supplierdao()

        override fun doInBackground(vararg p0: Unit?) {
            supplierdao?.insert(supplier("PT. Nukee", "Menjual bahan baku rumah tangga", "nidyanuke12@gmail.com","08226582992","Kamal Tengah Mantab"))
        }
    }

}