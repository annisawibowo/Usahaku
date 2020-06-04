package com.example.usahaku.Database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [pembelian::class], version = 1)
abstract class pembeliandb : RoomDatabase() {

    abstract fun pembeliandao(): pembeliandao
    companion object {
        private var instance: pembeliandb? = null
        fun getInstance(context: Context): pembeliandb? {
            if (instance == null) {
                synchronized(pembeliandb::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        pembeliandb::class.java, "pembelian_database"  )
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

    class PopulateDbAsyncTask(db: pembeliandb?) : AsyncTask<Unit, Unit, Unit>() {
        private val pembeliandao = db?.pembeliandao()

        override fun doInBackground(vararg p0: Unit?) {
            pembeliandao?.insert(pembelian("PT. Mayor", "Minyal x 2 x 14000", "03/06/2020",50000))
        }
    }

}