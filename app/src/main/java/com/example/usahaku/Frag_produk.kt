package com.example.usahaku

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usahaku.Database.produk
import com.example.usahaku.Database.produkviewmodel
import com.example.usahaku.databinding.FragmentProdukBinding
import kotlinx.android.synthetic.main.fragment_produk.*

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class Frag_produk : Fragment() {

    private lateinit var binding: FragmentProdukBinding
   // private lateinit var produkviewmodel: produkviewmodel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        judul()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_produk,container,false)
        // Inflate the layout for this fragment
            return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_produk.layoutManager = LinearLayoutManager(this.requireContext())
        rv_produk.setHasFixedSize(true)
        val adapter = produkadapter()
        rv_produk.adapter = adapter

        var produkviewmodel:produkviewmodel = ViewModelProviders.of(this).get(produkviewmodel::class.java)
        produkviewmodel.getAllproduk().observe(this.viewLifecycleOwner, Observer <List<produk>>{
            adapter.submitList(it)
        })


        binding.buttonAddproduk.setOnClickListener{
            it.findNavController().navigate(R.id.action_frag_produk_to_tambahproduk)
        }
   //item touchhelper dan seterusnya

        ItemTouchHelper(
            object :ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT).or(ItemTouchHelper.DOWN)) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    AlertDialog.Builder(viewHolder.itemView.getContext())
                        // Judul
                        .setTitle("Warning")
                        // Pesan yang di tamopilkan
                        .setMessage("Ingin Dihapus ?")
                        .setPositiveButton("Ya", DialogInterface.OnClickListener(){ dialogInterface, i ->
                            produkviewmodel.delete(adapter.getprodukAt(viewHolder.adapterPosition))
                            Toast.makeText(activity, "Produk dihapus!", Toast.LENGTH_SHORT).show()
                        })
                        .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
                            Toast.makeText(activity, "Produk Tidak Terhapus", Toast.LENGTH_LONG).show()
                            adapter.notifyItemChanged(viewHolder.adapterPosition)
                        })
                        .show()
                }
            }
        ).attachToRecyclerView(rv_produk)
        adapter.setOnItemClickListener(object : produkadapter.OnItemClickListener {
            override fun onItemClick(produk:produk) {
                val bundle = Bundle()
                bundle.putInt(tambahproduk.EXTRA_ID, produk.id_produk)
                bundle.putString(tambahproduk.EXTRA_NAMA, produk.namaproduk)
                bundle.putString(tambahproduk.EXTRA_DESKRIPSI, produk.deskproduk)
                bundle.putInt(tambahproduk.EXTRA_JUAL, produk.hargajual)
                bundle.putInt(tambahproduk.EXTRA_HARGA, produk.hargapokok)
                bundle.putInt(tambahproduk.EXTRA_JUMLAH, produk.jumlah)
                bundle.putString(tambahproduk.EXTRA_SATUAN, produk.satuanproduk)

                when(view.id){
                    R.id.listitemproduk -> {
                        view.findNavController().navigate(R.id.action_home_fragment_to_profiletoko)
                    }
                }
                        }
        }
        )}
    private fun judul() {
        val getActivity = this.requireActivity() as MainActivity
        getActivity.supportActionBar?.title = "Barber Shop"
    }






}
