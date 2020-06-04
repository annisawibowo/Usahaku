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
import com.example.usahaku.Database.supplier
import com.example.usahaku.Database.supplierviewmodel
import com.example.usahaku.databinding.FragmentSupplierBinding
import kotlinx.android.synthetic.main.fragment_supplier.*
import kotlinx.android.synthetic.main.fragment_tambahsupplier.*

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class frag_supplier : Fragment() {


        // TODO: Rename and change types of parameters
        var KEY_FRG = "msg_fragment2"
        private lateinit var binding: FragmentSupplierBinding
         //private lateinit var produkviewmodel: produkviewmodel


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

            binding = DataBindingUtil.inflate(inflater,R.layout.fragment_supplier,container,false)
            // Inflate the layout for this fragment
            return binding.root

        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val msg = arguments?.getString(KEY_FRG)


            rv_supplier.layoutManager = LinearLayoutManager(this.requireContext())
            rv_supplier.setHasFixedSize(true)
            val adapter = supplieradapter()
            rv_supplier.adapter = adapter

            //Toast.makeText(activity,msg+"<-nih apa",Toast.LENGTH_LONG).show() ///BISA ANJAYYY PAKE BUNDLE DIBELAKANG ACTION_WHI WEHFI

            var supplierviewmodel: supplierviewmodel = ViewModelProviders.of(this).get(supplierviewmodel::class.java)
            supplierviewmodel.getAllsupplier() .observe(this.viewLifecycleOwner, Observer <List<supplier>>{
                adapter.submitList(it)
            })


            binding.buttonAddsupplier.setOnClickListener{
                it.findNavController().navigate(R.id.action_frag_supplier_to_frag_tambahsupplier)
            }
            //item touchhelper dan seterusnya

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
                            // Pesan yang di tamopilkan
                            .setMessage("Ingin Dihapus ?")
                            .setPositiveButton("Ya", DialogInterface.OnClickListener(){ dialogInterface, i ->
                                supplierviewmodel.delete(adapter.getsupplierAt(viewHolder.adapterPosition))

                                Toast.makeText(activity, "Supplier dihapus!", Toast.LENGTH_SHORT).show()
                            })
                            .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
                                Toast.makeText(activity, "Supplier Tidak Terhapus", Toast.LENGTH_SHORT).show()

                                adapter.notifyItemChanged(viewHolder.adapterPosition)
                            })
                            .show()
                    }
                }
            ).attachToRecyclerView(rv_supplier)
            adapter.setOnItemClickListener(object : supplieradapter.OnItemClickListener {
                override fun onItemClick(supplier: supplier) {
                    if (arguments != null) {
                        val bundle = Bundle()
                        bundle.putString("namasupplier", supplier.namasupplier)
                        bundle.putString("email", supplier.emailsupplier)
                        when (view.id) {
                            R.id.listsupplier -> {

                                view.findNavController().navigate(
                                    R.id.frag_tambahpembelian, bundle)//sek besok ae

                            }

                        }



                    }
                    else{//update

                        val bundle = Bundle()
                        bundle.putInt("id",supplier.id_supplier)
                        bundle.putString("namasupplier",supplier.namasupplier)
                        bundle.putString("desk",supplier.desksupplier)
                        bundle.putString("email", supplier.emailsupplier)
                        bundle.putString("notelp",supplier.notelpsup)
                        bundle.putString("alamat",supplier.alamatsup)

                        view.findNavController().navigate(R.id.action_frag_supplier_to_frag_tambahsupplier, bundle)
                    }


                }
            }
            )}




    }
