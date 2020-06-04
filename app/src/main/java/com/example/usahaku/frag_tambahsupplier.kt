package com.example.usahaku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.usahaku.Database.supplier
import com.example.usahaku.Database.supplierviewmodel
import com.example.usahaku.databinding.FragmentTambahsupplierBinding
import kotlinx.android.synthetic.main.fragment_tambahsupplier.*

class frag_tambahsupplier : Fragment() {
    private lateinit var binding: FragmentTambahsupplierBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tambahsupplier,container,false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var supplierviewmodel: supplierviewmodel = ViewModelProviders.of(this).get(supplierviewmodel::class.java)
        if (arguments != null) {
        masukin()
        }


        binding.savesupplier.setOnClickListener{
            if (arguments != null) {
                //masukin()
                var idt = arguments?.getInt("id")
               // Toast.makeText(context," apa sih"+idt,Toast.LENGTH_SHORT).show()


                val newsupplier = supplier (
                    binding.namasupplier.text.toString(),binding.desksupplier.text.toString(),binding.emailsupplier.text.toString(),binding.notelpsup.text.toString()
                    ,binding.alamatsupplier.text.toString()///ANJAY KALO INTEGER GIMANA NGAMBILNYA .tostring.toint
                )
                newsupplier.id_supplier = idt.toString().toInt()
                supplierviewmodel.update(newsupplier)
                //this.dismiss()
                this.findNavController().popBackStack()
            }
            else{
                val newsupplier = supplier (
                    binding.namasupplier.text.toString(),binding.desksupplier.text.toString(),binding.emailsupplier.text.toString(),binding.notelpsup.text.toString()
                    ,binding.alamatsupplier.text.toString()///ANJAY KALO INTEGER GIMANA NGAMBILNYA .tostring.toint
                )
                supplierviewmodel.insert(newsupplier)
                //this.dismiss()
                this.findNavController().popBackStack()
            }

        }

    }
    fun masukin(){

        namasupplier.setText(arguments?.getString("namasupplier").toString())
        desksupplier.setText(arguments?.getString("desk").toString())
        emailsupplier.setText(arguments?.getString("email").toString())
        notelpsup.setText(arguments?.getString("notelp").toString())
        alamatsupplier.setText(arguments?.getString("alamat").toString())
    }
}
