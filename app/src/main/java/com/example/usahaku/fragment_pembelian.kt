package com.example.usahaku

import android.app.AlertDialog
import android.content.DialogInterface
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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usahaku.Database.pembelian
import com.example.usahaku.Database.pembelianviewmodel
import com.example.usahaku.databinding.FragPembelianBinding
import kotlinx.android.synthetic.main.frag_pembelian.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_pembelian.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_pembelian : Fragment() {

    private lateinit var binding: FragPembelianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.frag_pembelian,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_beli.layoutManager = LinearLayoutManager(this.requireContext())
        rv_beli.setHasFixedSize(true)
        val adapter = pembelianadapter()
        rv_beli.adapter = adapter
        var pembelianviewmodel:pembelianviewmodel = ViewModelProviders.of(this).get(pembelianviewmodel::class.java)
        pembelianviewmodel.getAllpembelian().observe(this.viewLifecycleOwner, Observer <List<pembelian>>{
            adapter.submitList(it)
        })
        ItemTouchHelper(
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT).or(
                    ItemTouchHelper.DOWN)) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    AlertDialog.Builder(viewHolder.itemView.getContext())
                        // Judul
                        .setTitle("Warning")
                        // Pesan yang di tamopilkan//
                        .setMessage("Ingin Dihapus ?")
                        .setPositiveButton("Ya", DialogInterface.OnClickListener(){ dialogInterface, i ->
                            pembelianviewmodel.delete(adapter.getpembelianAt(viewHolder.adapterPosition))
                            Toast.makeText(activity, "Pembelian dihapus!", Toast.LENGTH_SHORT).show()
                        })
                        .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
                            Toast.makeText(activity, "Pembelian Tidak Terhapus", Toast.LENGTH_LONG).show()
                            adapter.notifyItemChanged(viewHolder.adapterPosition)
                        })
                        .show()
                }
            }
        ).attachToRecyclerView(rv_beli)
        adapter.setOnItemClickListener(object : pembelianadapter.OnItemClickListener {
            override fun onItemClick(pembelian: pembelian) {
                //penjualan
               // Toast.makeText(context,"hai="+penjualan.produk, Toast.LENGTH_SHORT).show()

                var beli = pembelian(pembelian.namasupplierp,pembelian.produkdes,pembelian.tanggalpesan,pembelian.totalpembelian)

                fragmentdialog_upbeli(beli).show(childFragmentManager,"")



            }
        })


        binding.buttonAddbeli.setOnClickListener{
            it.findNavController().navigate(R.id.action_fragment_pembelian_to_frag_tambahpembelian)
        }

    }
}
