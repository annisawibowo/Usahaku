package com.example.usahaku

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.usahaku.Database.produk
import com.example.usahaku.Database.produkdb
import com.example.usahaku.Database.produkviewmodel
import com.example.usahaku.databinding.FragmentTambahprodukBinding
import kotlinx.android.synthetic.main.fragment_tambahproduk.*


class tambahproduk : Fragment() {
  //  private lateinit var produkviewmodel: produkviewmodel
    companion object {
        const val EXTRA_ID = "com.example.usahaku.EXTRA_ID"
        const val EXTRA_NAMA = "com.example.usahaku.EXTRA_NAMA"
        const val EXTRA_DESKRIPSI = "com.example.usahaku.EXTRA_DESKRIPSI"
        const val EXTRA_HARGA = "com.example.usahaku.EXTRA_HARGA"
        const val EXTRA_JUAL = "com.example.usahaku.EXTRA_JUAL"
        const val EXTRA_SATUAN = "com.example.usahaku.EXTRA_SATUAN"
        const val EXTRA_JUMLAH = "com.example.usahaku.EXTRA_JUMLAH"
    }
   private lateinit var binding: FragmentTambahprodukBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tambahproduk,container,false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var produkviewmodel:produkviewmodel = ViewModelProviders.of(this).get(produkviewmodel::class.java)
        binding.saveproduk.setOnClickListener{

            val newproduk = produk (
                binding.namaProduk.text.toString(),binding.deskproduk.text.toString(),binding.hargapokok.text.toString().toInt(),binding.hargajual.text.toString().toInt(),binding.jumlahproduk.text.toString().toInt(),binding.satuan.text.toString()
                ///ANJAY KALO INTEGER GIMANA NGAMBILNYA
            )
            produkviewmodel.insert(newproduk)
            view.findNavController().navigate(R.id.action_tambahproduk_to_frag_produk)
        }
    }


private fun save(){

  //  val newproduk = produk (
    //    binding.namaProduk.text.toString(),binding.deskproduk.text.toString(),binding.hargapokok.inputType,binding.hargajual.inputType,binding.jumlahproduk.inputType,binding.satuan.text.toString()

   // )
 //   produkviewmodel.insert(newproduk)


}






    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }


}
