package com.example.usahaku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.usahaku.Database.supplier
import kotlinx.android.synthetic.main.item_supplier.view.*


class supplieradapter : ListAdapter<supplier, supplieradapter.supplierHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<supplier>() {
            override fun areItemsTheSame(oldItem: supplier, newItem: supplier): Boolean {
                return oldItem.id_supplier == newItem.id_supplier
            }
            override fun areContentsTheSame(oldItem: supplier, newItem: supplier): Boolean {
                return oldItem.namasupplier == newItem.namasupplier && oldItem.emailsupplier == newItem.emailsupplier
                        && oldItem.notelpsup == newItem.notelpsup && oldItem.desksupplier == newItem.desksupplier
                        && oldItem.alamatsup == newItem.alamatsup
            }
        }
    }
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): supplierHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_supplier, parent, false)
        return supplierHolder(itemView)
    }
    override fun onBindViewHolder(holder: supplierHolder, position: Int) {
        val currentsupplier: supplier = getItem(position)
        holder.textViewnama.text = currentsupplier.namasupplier
        holder.textViewdesk.text = currentsupplier.desksupplier

    }
    fun getsupplierAt(position: Int): supplier {
        return getItem(position)
    }
    inner class supplierHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
        var textViewnama: TextView = itemView.nama_supplier
        var textViewdesk: TextView = itemView.desksupplier


    }
    interface OnItemClickListener {
        fun onItemClick(supplier: supplier)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}