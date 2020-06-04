package com.example.usahaku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.usahaku.Database.pembelian
import kotlinx.android.synthetic.main.fragment_tambahpembelian.view.*
import kotlinx.android.synthetic.main.item_pembelian.view.*


class pembelianadapter : ListAdapter<pembelian, pembelianadapter.pembelianHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<pembelian>() {
            override fun areItemsTheSame(oldItem: pembelian, newItem: pembelian): Boolean {
                return oldItem.id_pembelian == newItem.id_pembelian
            }
            override fun areContentsTheSame(oldItem: pembelian, newItem:pembelian): Boolean {
                return oldItem.namasupplierp== newItem.namasupplierp && oldItem.produkdes == newItem.produkdes
                        && oldItem.tanggalpesan == newItem.tanggalpesan && oldItem.totalpembelian == newItem.totalpembelian
            }
        }
    }
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pembelianHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_pembelian, parent, false)
        return pembelianHolder(itemView)
    }
    override fun onBindViewHolder(holder: pembelianHolder, position: Int) {
        val current: pembelian = getItem(position)
        holder.textViewtanggal.text = current.tanggalpesan
        holder.textViewhargajual.text = current.totalpembelian.toString()

    }
    fun getpembelianAt(position: Int): pembelian {
        return getItem(position)
    }
    inner class pembelianHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
        var textViewtanggal: TextView = itemView.tanggalembelian
        var textViewhargajual: TextView = itemView.hargapembelian


    }
    interface OnItemClickListener {
        fun onItemClick(pembelian: pembelian)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}