package com.example.usahaku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.usahaku.Database.*
import com.example.usahaku.databinding.FragmentdialogPilprodukBinding
import kotlinx.android.synthetic.main.fragmentdialog_pilproduk.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragdialog_pilproduk.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragdialog_pilproduk (
    private val Produk: produk

) : DialogFragment() {
    var no = 1
    //private lateinit var tempproduk: tempproduk
    private lateinit var binding: FragmentdialogPilprodukBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragmentdialog_pilproduk,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Produk.id_produk == 0){
            masukinpesan()
            binding.pilihjmlproduk.setOnClickListener{
                var jumlahpesan = pilihjmlpro.text.toString().toInt()
                val hargatotal = Produk.hargapokok * jumlahpesan
                var tempppesrodukvm: temppesprodukvm= ViewModelProvider(this).get(temppesprodukvm::class.java)
                val inproduk = temppesproduk(Produk.namaproduk,Produk.hargapokok,hargatotal,jumlahpesan,Produk.satuanproduk)
                tempppesrodukvm.insert(inproduk)
                this.dismiss()
                this.findNavController().popBackStack()
            }
        }
        else{
            masukkin()
            binding.pilihjmlproduk.setOnClickListener{
                var produkviewmodel: produkviewmodel = ViewModelProviders.of(this).get(produkviewmodel::class.java)
                var jumlahstok = Produk.jumlah

                var tempprodukViewModel: tempprodukViewModel = ViewModelProvider(this).get(tempprodukViewModel::class.java)

                var jumlahbeli = pilihjmlpro.text.toString().toInt()
                if (jumlahstok >= jumlahbeli){
                    val jmlnew = jumlahstok - jumlahbeli

                    val hargatotal = Produk.hargajual * jumlahbeli
                    val inproduk = tempproduk(Produk.namaproduk,Produk.hargajual,hargatotal,jumlahbeli,Produk.satuanproduk)
                    tempprodukViewModel.insert(inproduk) //
                    //Toast.makeText(context,jmlnew.toString(),Toast.LENGTH_LONG).show()
                    val upproduk = produk(Produk.namaproduk,Produk.deskproduk,Produk.hargapokok,Produk.hargajual,jmlnew,Produk.satuanproduk)
                    upproduk.id_produk = Produk.id_produk
                    //Toast.makeText(context,upproduk.id_produk.toString(),Toast.LENGTH_LONG).show()
                    produkviewmodel.update(upproduk)

                    this.dismiss()
                    this.findNavController().popBackStack()

                }
                else{
                    binding.pilihjmlpro.error = "Jumlah Terlalu Banyak"
                }
            }
        }


       // var harga = tempproduk.hargatot
        //Toast.makeText(context,harga.toString(),Toast.LENGTH_LONG).show()


    }


    private fun masukkin(){
        namapro.setText(Produk.namaproduk)
        hargapro.setText(Produk.hargajual.toString())
        jmlpro.setText(Produk.jumlah.toString())
        stnproduk.setText(Produk.satuanproduk)

    }
    private fun masukinpesan(){

        namapro.setText(Produk.namaproduk)
        hargapro.setText(Produk.hargapokok.toString())
        jmlpro.setText(Produk.jumlah.toString())
        stnproduk.setText(Produk.satuanproduk)

    }
}
