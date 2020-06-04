package com.example.usahaku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.usahaku.Database.pembelian
import com.example.usahaku.databinding.FragdialogUpbeliBinding
import kotlinx.android.synthetic.main.fragdialog_upbeli.*

class fragmentdialog_upbeli(
    private val beli : pembelian
) : DialogFragment() {
    private lateinit var binding: FragdialogUpbeliBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //masukin()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragdialog_upbeli,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        masukin()
    }

    fun masukin(){

        namasuppbl.setText(beli.namasupplierp)
        deskpbl.setText(beli.produkdes)
        tanggalpbl.setText(beli.tanggalpesan)
        totalpbl.setText(beli.totalpembelian.toString())
    }

}
