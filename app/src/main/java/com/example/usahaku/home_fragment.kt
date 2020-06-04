package com.example.usahaku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.navigation.findNavController


class home_fragment : Fragment() {

    //private lateinit var binding: home_fragment

    // TODO: Rename and change types of parameters
  //  private var listener: profiletoko.OnFragmentInteractionListener? = null

   /// override fun onCreate(savedInstanceState: Bundle?) {
     //   super.onCreate(savedInstanceState)
    //    arguments?.let {

   //     }
  //  }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        view.findViewById<ImageView>(R.id.produkhome).setOnClickListener{
            view.findNavController().navigate(R.id.action_home_fragment_to_frag_produk)
        }
        view.findViewById<ImageButton>(R.id.buttonpelanggan).setOnClickListener{
            view.findNavController().navigate(R.id.action_home_fragment_to_fragment_pelanggan)
        }
        view.findViewById<ImageButton>(R.id.penjualanhome).setOnClickListener{
            view.findNavController().navigate(R.id.action_home_fragment_to_fragment_penjualan)
        }
        view.findViewById<ImageButton>(R.id.supplierrrr).setOnClickListener{
            view.findNavController().navigate(R.id.action_home_fragment_to_frag_supplier)
        }
        view.findViewById<ImageButton>(R.id.pembelianhome).setOnClickListener{
            view.findNavController().navigate(R.id.action_home_fragment_to_fragment_pembelian)
        }

    }




}
