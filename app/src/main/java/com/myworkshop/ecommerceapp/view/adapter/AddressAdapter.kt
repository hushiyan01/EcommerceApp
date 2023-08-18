package com.myworkshop.ecommerceapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.AddressItemBinding
import com.myworkshop.ecommerceapp.model.local.entity.po.AddressView

class AddressAdapter(val addressViews: List<AddressView>, val context: Context) : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {
    private lateinit var binding: AddressItemBinding
    private var isSelected = false

    inner class AddressViewHolder(binding: AddressItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val type = binding.tvAddressType
        private val address = binding.tvAddress
        private val button = binding.btnAddressSelector
        var isCurSelected = false

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            type.text = addressViews[position].type
            address.text = addressViews[position].address
            button.setOnClickListener {
                if (!isSelected) {
                    isSelected = true
                    isCurSelected = true
                    it.setBackgroundResource(R.drawable.button_selected)
                    addressViews[position].isSelected = true
                    notifyDataSetChanged()
                } else if (isCurSelected) {
                    isSelected = false
                    isCurSelected = false
                    it.setBackgroundResource(R.drawable.button_unselected)
                    addressViews[position].isSelected = false
                    notifyDataSetChanged()
                } else {
                    Toast.makeText(context,"you can only select one address!",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = AddressItemBinding.inflate(inflater, parent, false)
        return AddressViewHolder(binding)
    }

    override fun getItemCount(): Int = addressViews.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(position)
    }
}