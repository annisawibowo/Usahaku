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
@Entity
    (tableName = "tabel_pelanggan")
data class pelanggan(
    var namapelanggan: String,
    var emailpelanggan: String,
    var alamatpelanggan: String,
    var notelppelanggan: String)
   {
    @PrimaryKey(autoGenerate = true)
    var id_pelanggan: Int = 0
}

@Entity
    (tableName = "tabel_penjualan")
data class penjualan(
    var namapelanggan: String,
    var namaproduk: String,
    var tanggaljual: String,
    var jumlahproduk: Int,
    var totalpembayaran:Int)
{
    @PrimaryKey(autoGenerate = true) var id_penjualan: Int = 0
    @PrimaryKey(autoGenerate = false) var id_pelanggan: Int = 0
    @PrimaryKey(autoGenerate = false) var id_produk: Int = 0
    @PrimaryKey(autoGenerate = false) var namatransaksi : String = ""


}

