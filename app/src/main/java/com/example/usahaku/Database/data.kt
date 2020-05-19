package com.example.usahaku.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
    (tableName = "table_toko")
data class toko(
    var namatoko: String,
    var alamat: String,
    var email: String,
    var notlp: String ) {
    @PrimaryKey(autoGenerate = true)
    var id_toko: Int = 0
}
@Entity
    (tableName = "tabel_produk")
data class produk(
    var namaproduk: String,
    var deskproduk: String,
    var hargapokok: Int,
    var hargajual: Int,
    var jumlah: Int,
    var satuanproduk: String) {
    @PrimaryKey(autoGenerate = true)
    var id_produk: Int = 0
}