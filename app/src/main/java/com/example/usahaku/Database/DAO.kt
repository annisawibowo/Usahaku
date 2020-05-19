package com.example.usahaku.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TokoDao {

    @Insert
    fun insert(toko: toko)

    @Update
    fun update(toko: toko)

   @Query("SELECT * FROM table_toko")
    fun getAlltoko(): LiveData<List<toko>>

}
@Dao
interface Produkdao {

    @Insert
    fun insert(produk: produk)

    @Update
    fun update(produk: produk)

    @Delete
    fun delete(produk: produk)

    @Query("DELETE FROM tabel_produk")
    fun deleteAllproduk()

    @Query("SELECT * FROM tabel_produk")
    fun getAllproduk(): LiveData<List<produk>>

}


