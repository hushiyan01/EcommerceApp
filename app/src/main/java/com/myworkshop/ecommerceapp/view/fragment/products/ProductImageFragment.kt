package com.myworkshop.ecommerceapp.view.fragment.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.FragmentProductImageBinding
import com.squareup.picasso.Picasso

class ProductImageFragment(private val imageUrl:String) : Fragment() {
    private lateinit var binding:FragmentProductImageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductImageBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(imageUrl).into(binding.ivProductDetailImage)
    }

}