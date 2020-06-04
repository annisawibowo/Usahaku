package com.example.usahaku

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.usahaku.Database.pembelian
import com.example.usahaku.Database.pembelianviewmodel
import com.example.usahaku.Database.temppesprodukdb
import com.example.usahaku.Database.temppesprodukvm
import com.example.usahaku.databinding.FragmentTambahpembelianBinding
import kotlinx.android.synthetic.main.fragment_tambahpembelian.*
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.spec.DESKeySpec

class frag_tambahpembelian : Fragment() {
    var keyp = "Pembelian"
    private lateinit var binding: FragmentTambahpembelianBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tambahpembelian,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_pesanproduk.layoutManager = LinearLayoutManager(this.requireContext())
        rv_pesanproduk.setHasFixedSize(true)
        val adapter = temppesprodukAdapter(this.requireContext())
        rv_pesanproduk.adapter = adapter
        val c1: Calendar = Calendar.getInstance()
        val year = c1.get(Calendar.YEAR)
        val month = c1.get(Calendar.MONTH)
        val day = c1.get(Calendar.DAY_OF_MONTH)
        val cdf1 = SimpleDateFormat("d/M/yyyy")
        val strdate1: String = cdf1.format(c1.getTime())
        binding.tanggalpembelian.setText(strdate1)

            binding.tanggalpembelian.setOnClickListener{
                val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener {
                        view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    var get = "" + dayOfMonth.toString() + "/" + month.toString() + "/" + year.toString()
                    binding.tanggalpembelian.setText(get)
                }, year, month, day)
                dpd.show()
        }
        var tempppesrodukvm: temppesprodukvm= ViewModelProvider(this).get(temppesprodukvm::class.java)
        var pembelianviewmodel: pembelianviewmodel = ViewModelProviders.of(this).get(pembelianviewmodel::class.java)
        if (arguments != null) {
            var namasup = arguments?.getString("namasupplier")
            var email = arguments?.getString("email")
            pesansupplier.setText(namasup)

        }
        var db = Room.databaseBuilder(requireContext(),temppesprodukdb :: class.java,"tabel_temppesproduk").build()
        tempppesrodukvm.alltempspes.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer { temps -> temps?.let{
            adapter.settemps(it)
                adapter.setOnClickListener {
                val current = temps[it]

                tempppesrodukvm.delete(current)
            }}
        })

        binding.pilihsupplier.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(keyp,"pesensup")
            it.findNavController().navigate(R.id.frag_supplier,bundle)

        }
        binding.pesanproduk.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(keyp,"pesenprod")
            it.findNavController().navigate(R.id.frag_produk,bundle)

        }
        binding.savepembelian.setOnClickListener{
            fun coba(){
                var tanggall = binding.tanggalpembelian.text.toString()
                val t = Thread(Runnable {
                    var hartot = 0
                    var produkk = mutableListOf("")
                    var jumlah = mutableListOf("")
                    var satuan = mutableListOf("")
                    var harsat = mutableListOf("")
                    var i = 1
                    db.temppesprodukdao().alldatatemppes().forEach {
                        var harga = it.hargapestot
                        hartot += harga
                        produkk.add(i,it.namaprodukpesbeli)
                        jumlah.add(i,it.jumlahpesbeli.toString())
                        satuan.add(i,it.satuanpesprodukbeli)
                        harsat.add(i,it.hargasatuanpesbeli.toString())
                        i++
                    }

                    var no = i-1
                    var namasup = binding.pesansupplier.text.toString()
                    var tanggal = binding.tanggalpembelian.text.toString()

                    if (no == 1){
                        var newpem = pembelian(namasup,produkk[1]+" : "+jumlah[1]+" "+satuan[1]+" x "+harsat[1],tanggall,hartot.toString().toInt())
                        pembelianviewmodel.insert(newpem)
                        var desk = produkk[1]+" : "+jumlah[1]+" "+satuan[1]+" x "+harsat[1]
                        var email = arguments?.getString("email")
                        this.findNavController().popBackStack()
                        this.findNavController().popBackStack()
                        this.findNavController().popBackStack()
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.setType("text/plain")
                        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>(email.toString()))
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Pesanan Produk")
                        intent.putExtra(Intent.EXTRA_TEXT, desk)
                        startActivity(Intent.createChooser(intent,"Ingin Mengirim Email ?"))

                    }
                    else if (no == 2){
                        var newpem = pembelian(namasup,produkk[1]+" : "+jumlah[1]+" "+satuan[1]+" x "+harsat[1]+"\n"+produkk[2]+" : "+jumlah[2]+" "+satuan[2]+" x "+harsat[2],tanggall,hartot.toString().toInt())
                        pembelianviewmodel.insert(newpem)
                        var desk = produkk[1]+" : "+jumlah[1]+" "+satuan[1]+" x "+harsat[1]+"\n"+produkk[2]+" : "+jumlah[2]+" "+satuan[2]+" x "+harsat[2]
                        var email = arguments?.getString("email")
                        this.findNavController().popBackStack()
                        this.findNavController().popBackStack()
                        this.findNavController().popBackStack()
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.setType("text/plain")
                        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>(email.toString()))
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Pesanan Produk")
                        intent.putExtra(Intent.EXTRA_TEXT, desk)
                        startActivity(Intent.createChooser(intent,"Ingin Mengirim Email ?"))

                    }

                    // Log.i("@apaan","i ${i}") i-1
                })
                t.start()
            }

                coba()

        }

    }
   }
